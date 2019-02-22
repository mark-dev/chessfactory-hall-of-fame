package ru.chessfactory.pgn.analysis.test;


import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.chessfactory.pgn.analysis.core.pipeline.GameToAggregatesTransformer;
import ru.chessfactory.pgn.analysis.core.pipeline.PGNToGameTransformator;
import ru.chessfactory.pgn.analysis.core.util.BasePGNProducer;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

@Slf4j
public class SimpleTestExample {

    @Test
    @SneakyThrows
    public void calcAggregates() {
        InputStream pgn = this.getClass().getClassLoader().getResourceAsStream("1.pgn");

        Game game = new PGNReader(pgn,"").parseGame();

        GameToAggregatesTransformer gameToAggregates = new GameToAggregatesTransformer();
        GameAggregates aggregates = gameToAggregates.transformPayload(game);

        log.info("GameAggregates: {}", aggregates);
    }
}
