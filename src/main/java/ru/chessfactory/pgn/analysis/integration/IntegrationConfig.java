package ru.chessfactory.pgn.analysis.integration;

import chesspresso.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collection;

@EnableIntegration
@Configuration
@Slf4j
public class IntegrationConfig {
    public static final String DIRECTORY = "/media/mark/DATAPART1/lichess_db_standard_rated_2018-12.pgn.bz2";
    public static final int BATCH_SIZE = 1000;
    public static final long PGN_READ_LIMIT = 50000;

    @Bean
    public IntegrationFlow fileReadingMessageSource() {
        return IntegrationFlows.from(new PGNMessageSource(DIRECTORY, PGN_READ_LIMIT),
                c -> c.poller(filePoller()))
                .channel(c -> c.executor(pgnHandleThreadPool()))
                .transform(new PGNToGameTransformator())
                .filter((GenericSelector<Game>) g -> g != PGNToGameTransformator.NO_GAME)
                .transform(new GameAggregatesTransformator())
                .aggregate(
                        a -> a.correlationStrategy(message -> 1)
                                .releaseStrategy(group -> group.size() == BATCH_SIZE)
                                .outputProcessor(g -> new GenericMessage<Collection<Message<?>>>(g.getMessages()))
                                .expireGroupsUponCompletion(true)
                )
                .channel(c -> c.executor(dbWriteThreadPool()))
                .handle(batchDBSaver())
                .get();
    }

    @Bean
    public DBSaver batchDBSaver() {
        return new DBSaver();
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
    public TaskExecutor pgnHandleThreadPool() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setThreadGroupName("pgnHandleThreadPool");
        return pool;
    }

    @Bean
    public TaskExecutor dbWriteThreadPool() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setThreadGroupName("dbWriteThreadPool");
        return pool;
    }

    @Bean
    public PollerMetadata filePoller() {
        PollerMetadata m = new PollerMetadata();
        m.setTaskExecutor(fileReadThread());
        return m;
    }
}
