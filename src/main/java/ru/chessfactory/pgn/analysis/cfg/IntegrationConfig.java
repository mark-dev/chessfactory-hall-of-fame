package ru.chessfactory.pgn.analysis.cfg;

import chesspresso.game.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.aggregator.MessageCountReleaseStrategy;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.support.management.AggregatingMetricsFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.support.PeriodicTrigger;
import ru.chessfactory.pgn.analysis.cfg.integration.IntegrationGateway;
import ru.chessfactory.pgn.analysis.core.pipeline.GameAggregatesBatchJpaProcessor;
import ru.chessfactory.pgn.analysis.core.pipeline.GameToAggregatesTransformer;
import ru.chessfactory.pgn.analysis.core.pipeline.PGNMessageSource;
import ru.chessfactory.pgn.analysis.core.pipeline.PGNToGameTransformator;
import ru.chessfactory.pgn.analysis.core.misc.PGNGatewayProducer;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@EnableIntegration
@Configuration
@EnableIntegrationManagement(defaultCountsEnabled = "true", defaultStatsEnabled = "true")
public class IntegrationConfig {
    public static final String PGN_QUEUE_NAME = "pgnQueue";


    @Bean(name = PGN_QUEUE_NAME)
    public QueueChannel pgnQueue(@Value("${chessfactory.max-pgn-in-flight}") int queueLen) {
        return new QueueChannel(queueLen);
    }

    @Bean
    public AggregatingMetricsFactory amf() {
        return new AggregatingMetricsFactory(10000);
    }

    @Bean
    public PGNGatewayProducer prod(@Value("${chessfactory.file}") String fileName,
                                   @Value("${chessfactory.readlimit}") long readLimit,
                                   IntegrationGateway gateway) {
        return new PGNGatewayProducer(fileName, readLimit, gateway);
    }

    @Bean
    public IntegrationFlow fileReadingMessageSource(@Value("${chessfactory.file}") String fileName,
                                                    @Value("${chessfactory.readlimit}") long readLimit,
                                                    @Value("${chessfactory.db-writer.batch-size}") int batchSize) {
        return IntegrationFlows.from(new PGNMessageSource(fileName, readLimit),
                c -> c.poller(filePollerMetadata()))
//                .split()
                .channel(PGN_QUEUE_NAME)
                .transform(new PGNToGameTransformator(), s -> s.poller(pgnPollerMetadata()))
                .filter((GenericSelector<Game>) g -> g != PGNToGameTransformator.NO_GAME)
                .transform(new GameToAggregatesTransformer())
                .aggregate(
                        a -> a.correlationStrategy(message -> 1)
                                .releaseStrategy(new MessageCountReleaseStrategy(batchSize))
                                .outputProcessor(g -> new GenericMessage<Collection<Message<?>>>(g.getMessages()))
                                .groupTimeout(TimeUnit.SECONDS.toMillis(1))
                                .sendPartialResultOnExpiry(true)
                                .expireGroupsUponCompletion(true)

                )
                .channel(c -> c.executor(dbWriteThreadPool()))
                .handle(batchDBSaver())
                .get();
    }

    @Bean
    public GameAggregatesBatchJpaProcessor batchDBSaver() {
        return new GameAggregatesBatchJpaProcessor();
    }

    @Bean
    public TaskExecutor fileReadThread() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(1);
        pool.setMaxPoolSize(1);
        pool.setThreadGroupName("fileReadThread");
        return pool;
    }

    @Bean
    @ConfigurationProperties(prefix = "chessfactory.agg-calc.pool")
    public TaskExecutor pgnHandleThreadPool() {
        return new ThreadPoolTaskExecutor();
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
        PeriodicTrigger trigger = new PeriodicTrigger(0, TimeUnit.MICROSECONDS);
        m.setTrigger(trigger);
        return m;
    }

    @Bean
    public PollerMetadata pgnPollerMetadata() {
        PollerMetadata m = new PollerMetadata();
        m.setTaskExecutor(pgnHandleThreadPool());
        return m;
    }

}
