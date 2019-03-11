package ru.chessfactory.pgn.analysis.core.pipeline;

import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.AbstractPayloadTransformer;

import java.io.ByteArrayInputStream;

@Slf4j
public class PGNToGameTransformator extends AbstractPayloadTransformer<ByteArrayInputStream, Game> {
    public static final Game NO_GAME = new Game();

    @Override
    @Timed(value = "chessfactory.pipeline.parsepgn",
            histogram = true,
            percentiles = {0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.99})
    public Game transformPayload(ByteArrayInputStream item) throws Exception {
        Game game = new PGNReader(item, "").parseGame();
        return game != null ? game : NO_GAME;
    }
}
