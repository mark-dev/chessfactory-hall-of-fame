package ru.chessfactory.hof.analysis.test;


import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.chessfactory.hof.core.GameAggregates;
import ru.chessfactory.hof.core.pipeline.GameToAggregatesTransformer;

import java.io.InputStream;

@Slf4j
public class SimpleTestExample {

    @Test
    @SneakyThrows
    public void calcAggregates() {
        InputStream pgn = this.getClass().getClassLoader().getResourceAsStream("4.pgn");

        Game game = new PGNReader(pgn,"").parseGame();

        GameToAggregatesTransformer gameToAggregates = new GameToAggregatesTransformer();
        GameAggregates aggregates = gameToAggregates.transformPayload(game);

        log.info("GameAggregates: {}", aggregates);
    }

    @Test
    public void databaseCount(){

    }
}
