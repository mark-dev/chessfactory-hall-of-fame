package ru.chessfactory.pgn.analysis.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;

import javax.persistence.EntityManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final String STRING_WILL_BE_INJECTED = null;
    private static final Long LONG_WILL_BE_INJECTED = null;

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job parseLichessJob(@Qualifier("lichessStep") Step step) throws IOException {
        return jobs.get("parseLichess").start(step).build();
    }

    @Bean
    public Step lichessStep(final ItemReader<ByteArrayInputStream> reader,
                            ItemProcessor<ByteArrayInputStream, GameAggregates> processor,
                            JpaItemWriter<GameAggregates> writer) throws IOException {
        return steps.get("lichessStep")
                .transactionManager(jpaTransactionManager())
                .<ByteArrayInputStream, GameAggregates>chunk(5000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(4);
        return asyncTaskExecutor;
    }
    @Bean
    @StepScope
    public ItemReader<ByteArrayInputStream> gameReader(@Value("#{jobParameters['fileName']}") String fileName,
                                                       @Value("#{jobParameters['readLimit']}") Long readLimit) throws IOException {
        return new PGNItemReader(fileName, readLimit);
    }

    @Bean
    @StepScope
    public ItemProcessor<ByteArrayInputStream, GameAggregates> processor() {
        return new PGNItemProcessor();
    }

    @Bean
    @StepScope
    public JpaItemWriter<GameAggregates> jpaItemWriter() {
        JpaItemWriter<GameAggregates> aggWriter = new JpaItemWriter<>();
        aggWriter.setEntityManagerFactory(emf);
        return aggWriter;
    }

    @Bean
    @Qualifier("jpaTrx")
    public PlatformTransactionManager jpaTransactionManager() {
        return new JpaTransactionManager(emf);
    }
}


