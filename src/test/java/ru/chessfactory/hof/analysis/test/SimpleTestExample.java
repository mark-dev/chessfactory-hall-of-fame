package ru.chessfactory.hof.analysis.test;


import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.messaging.support.GenericMessage;
import ru.chessfactory.hof.commons.GameAggregates;
import ru.chessfactory.hof.core.pipeline.GameToAggregatesTransformer;

import java.io.InputStream;

@Slf4j
public class SimpleTestExample {

    private MeterRegistry meterRegistry = new LoggingMeterRegistry();

    @Test
    @SneakyThrows
    public void calcAggregates() {
        InputStream pgn = this.getClass().getClassLoader().getResourceAsStream("4.pgn");

        Game game = new PGNReader(pgn, "").parseGame();

        GameToAggregatesTransformer gameToAggregates = new GameToAggregatesTransformer(meterRegistry);
        GenericMessage<Game> msg = new GenericMessage<>(game);
        GameAggregates aggregates = gameToAggregates.doTransform(msg);

        log.info("GameAggregates: {}", aggregates);
    }

    @Test
    public void databaseCount() {

    }
}
