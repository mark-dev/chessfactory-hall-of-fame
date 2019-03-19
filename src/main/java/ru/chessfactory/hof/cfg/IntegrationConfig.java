package ru.chessfactory.hof.cfg;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.aggregator.MessageCountReleaseStrategy;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.FileListFilter;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.support.PeriodicTrigger;
import ru.chessfactory.hof.cfg.integration.IntegrationGateway;
import ru.chessfactory.hof.core.pipeline.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@EnableIntegration
@Configuration
@EnableIntegrationManagement(defaultCountsEnabled = "true", defaultStatsEnabled = "true")
@Slf4j
public class IntegrationConfig {
    public static final String PGN_QUEUE_NAME = "pgnQueue";
    public static final String MOVE_FILE_AFTER_PROCESSING_BEAN_NAME = "moveFileAfterProcessingHandler";


    @Bean(name = PGN_QUEUE_NAME)
    public QueueChannel pgnQueue(@Value("${chessfactory.file-read.max-pgn-in-flight}") int queueLen) {
        return new QueueChannel(queueLen);
    }


    @Bean
    public IntegrationFlow readFilesAndSplitToPGN(FileReadingMessageSource directoryFileSource,
                                                  @Qualifier(MOVE_FILE_AFTER_PROCESSING_BEAN_NAME)
                                                          FileWritingMessageHandler moveFileAfterProcessing,
                                                  FileToPGNQueueAdapter producer) {
        return
                //Read files from target directory
                IntegrationFlows.from(directoryFileSource, s -> s.poller(scanDirPollerMetadata()))
                        //split to PGN games and produce to pgnQueue
                        .channel(MessageChannels.executor(fileReadThread()))
                        .log("file fetched from directory")
                        .handle(producer)
                        .log("file readed fully(or readlimit)")
                        .handle(moveFileAfterProcessing)
                        .log("file moved to done dir")
                        //print file info after finish
                        .log()
                        .get();
    }

    @Bean
    public IntegrationFlow processingPipeline(@Value("${chessfactory.db-writer.batch-size}") int batchSize,
                                              PGNToGameTransformator pgnParser,
                                              GameToAggregatesTransformer gameToAggregatesTransformer,
                                              JDBCBatchAggregateSaver dbSaver,
                                              SimpleGameFilter gameFilter) {
        return
                //Read from pgn queue
                IntegrationFlows.from(PGN_QUEUE_NAME)
                        //Parse PGN
                        .transform(pgnParser, s -> s.poller(pgnPollerMetadata()))
                        //Drop some games
                        .filter(gameFilter)
                        //Calculate aggregates
                        .transform(gameToAggregatesTransformer)
                        //Collect batch
                        .aggregate(
                                a -> a.correlationStrategy(message -> 1)
                                        .releaseStrategy(new MessageCountReleaseStrategy(batchSize))
                                        .outputProcessor(g -> new GenericMessage<Collection<Message<?>>>(g.getMessages()))
                                        .groupTimeout(TimeUnit.SECONDS.toMillis(5))
                                        .sendPartialResultOnExpiry(true)
                                        .expireGroupsUponCompletion(true)
                        )
                        //TODO: maybe move it up, before aggregation?
                        .channel(c -> c.executor(dbWriteThreadPool()))
                        //persist aggregates
                        .handle(dbSaver)
                        .get();
    }

    @Bean(name = MOVE_FILE_AFTER_PROCESSING_BEAN_NAME)
    public FileWritingMessageHandler moveFileAfterProcessing(@Value("${chessfactory.file-read.dir}") String rootDir) {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(Paths.get(rootDir, "done").toFile());
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        handler.setDeleteSourceFiles(true);
        return handler;
    }

    @Bean
    public FileReadingMessageSource readFilesMessageSource(@Value("${chessfactory.file-read.dir}") String rootDir,
                                                           @Value("${chessfactory.file-read.files-regexp}") String files) {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(rootDir));
        List<? extends FileListFilter<File>> filters = asList(new RegexPatternFileListFilter(files), new AcceptOnceFileListFilter<>());
        source.setFilter(new CompositeFileListFilter<>(filters));
        return source;
    }

    @Bean
    public SimpleGameFilter gameFilter() {
        return new SimpleGameFilter();
    }

    @Bean
    public PGNToGameTransformator pgnParser() {
        return new PGNToGameTransformator();
    }

    @Bean
    public FileToPGNQueueAdapter pgnProducer(IntegrationGateway gateway,
                                             @Value("${chessfactory.file-read.readlimit}") long readLimit) {
        return new FileToPGNQueueAdapter(gateway, readLimit);
    }

    @Bean
    public GameToAggregatesTransformer aggCalculator(MeterRegistry meterRegistry) {
        return new GameToAggregatesTransformer(meterRegistry);
    }


    @Bean
    public JDBCBatchAggregateSaver dbSaver(MeterRegistry registry) {
        return new JDBCBatchAggregateSaver(registry);
    }

    @Bean
    @ConfigurationProperties(prefix = "chessfactory.file-read.pool")
    public TaskExecutor fileReadThread() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    @Bean
    @ConfigurationProperties(prefix = "chessfactory.agg-calc.pool")
    public TaskExecutor pgnHandleThreadPool() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    @Bean
    @ConfigurationProperties(prefix = "chessfactory.db-writer.pool")
    public TaskExecutor dbWriteThreadPool() {
        return new ThreadPoolTaskExecutor();
    }


    @Bean
    public PollerMetadata pgnPollerMetadata() {
        PollerMetadata m = new PollerMetadata();
        m.setTaskExecutor(pgnHandleThreadPool());
        Trigger t = new PeriodicTrigger(5, TimeUnit.SECONDS);
        m.setTrigger(t);
        return m;
    }


    @Bean
    public PollerMetadata scanDirPollerMetadata() {
        PollerMetadata dirScanner = new PollerMetadata();
        dirScanner.setTrigger(new PeriodicTrigger(5, TimeUnit.SECONDS));
        dirScanner.setTaskExecutor(scanDirTaskExecutor());
        dirScanner.setMaxMessagesPerPoll(1);
        return dirScanner;
    }

    @Bean
    public TaskExecutor scanDirTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setQueueCapacity(0);
        pool.setMaxPoolSize(1);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        pool.setThreadNamePrefix("scanDir");
        return pool;
    }
}
