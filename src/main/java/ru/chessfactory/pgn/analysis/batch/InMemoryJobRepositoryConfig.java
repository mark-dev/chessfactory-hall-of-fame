package ru.chessfactory.pgn.analysis.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InMemoryJobRepositoryConfig extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(null);
    }
}
