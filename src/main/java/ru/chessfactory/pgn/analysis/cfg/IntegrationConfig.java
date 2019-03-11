package ru.chessfactory.pgn.analysis.cfg;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.support.PeriodicTrigger;
import ru.chessfactory.pgn.analysis.cfg.integration.IntegrationGateway;
import ru.chessfactory.pgn.analysis.core.pipeline.*;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableIntegration
@Configuration
@EnableIntegrationManagement(defaultCountsEnabled = "true", defaultStatsEnabled = "true")
@Slf4j
public class IntegrationConfig {
    public static final String PGN_QUEUE_NAME = "pgnQueue";


    @Bean(name = PGN_QUEUE_NAME)
    public QueueChannel pgnQueue(@Value("${chessfactory.max-pgn-in-flight}") int queueLen) {
        return new QueueChannel(queueLen);
    }


    @Bean
    public IntegrationFlow readFilesAndSplitToPGN(FileReadingMessageSource directoryFileSource,
                                                  FileToPGNProducerAdapter producer) {
        //Read files from directory, split to PGN games and produce to pgnQueue
        return IntegrationFlows.from(directoryFileSource, s -> s.poller(filePollerMetadata()))
                .handle(producer)
                .get();
    }

    @Bean
    public IntegrationFlow processingPipeline(@Value("${chessfactory.db-writer.batch-size}") int batchSize,
                                              PGNToGameTransformator pgnParser,
                                              GameToAggregatesTransformer gameToAggregatesTransformer,
                                              JDBCBatchAggregateSaver dbSaver,
                                              GameFilter gameFilter)
    {
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
                                        .groupTimeout(TimeUnit.SECONDS.toMillis(1))
                                        .sendPartialResultOnExpiry(true)
                                        .expireGroupsUponCompletion(true)

                        )
                        //save to database
                        .channel(c -> c.executor(dbWriteThreadPool()))
                        .handle(dbSaver)
                        .get();
    }


    @Bean
    public FileReadingMessageSource readFilesMessageSource(@Value("${chessfactory.dir}") String rootDir,
                                                           @Value("${chessfactory.files-regexp}") String files) {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(rootDir));
        source.setFilter(new RegexPatternFileListFilter(files));
        return source;
    }

    @Bean
    public GameFilter gameFilter() {
        return new GameFilter();
    }

    @Bean
    public PGNToGameTransformator pgnParser() {
        return new PGNToGameTransformator();
    }

    @Bean
    public FileToPGNProducerAdapter pgnProducer(IntegrationGateway gateway,
                                                @Value("${chessfactory.readlimit}") long readLimit) {
        return new FileToPGNProducerAdapter(gateway, readLimit);
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
    public TaskExecutor fileReadThread() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(1);
        pool.setMaxPoolSize(1);
        pool.setThreadGroupName("fileReadThread");
        pool.setQueueCapacity(1);
        //TODO: Проверить что реально работает.
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return pool;
    }

    @Bean
    @ConfigurationProperties(prefix = "chessfactory.agg-calc.pool")
    public TaskExecutor pgnHandleThreadPool() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        //TODO: Проверить что реально работает.
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return pool;
    }

    @Bean
    @ConfigurationProperties(prefix = "chessfactory.db-writer.pool")
    public TaskExecutor dbWriteThreadPool() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public PollerMetadata filePollerMetadata() {
        PollerMetadata m = new PollerMetadata();
        m.setTaskExecutor(fileReadThread());
        return m;
    }

    @Bean
    public PollerMetadata pgnPollerMetadata() {
        //TODO: Настроить реалистичную производительность тут. Тоетть Сделать триггер и кол-во на POLL ограничить.
        PollerMetadata m = new PollerMetadata();
        m.setTaskExecutor(pgnHandleThreadPool());
        Trigger t = new PeriodicTrigger(100, TimeUnit.MILLISECONDS);
        m.setTrigger(t);
        m.setMaxMessagesPerPoll(10000L);
        return m;
    }

}
