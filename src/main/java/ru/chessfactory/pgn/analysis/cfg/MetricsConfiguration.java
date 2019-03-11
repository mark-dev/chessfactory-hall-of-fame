package ru.chessfactory.pgn.analysis.cfg;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.support.management.AggregatingMetricsFactory;

@Configuration
@EnableAspectJAutoProxy
public class MetricsConfiguration {
    //for @Timed annotation
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    //spring intergration metrics
    @Bean
    public AggregatingMetricsFactory amf() {
        return new AggregatingMetricsFactory(10000);
    }
}
