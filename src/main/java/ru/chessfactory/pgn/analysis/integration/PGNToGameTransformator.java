package ru.chessfactory.pgn.analysis.integration;

import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import org.springframework.integration.transformer.AbstractPayloadTransformer;

import java.io.ByteArrayInputStream;

public class PGNToGameTransformator extends AbstractPayloadTransformer<ByteArrayInputStream, Game> {
    public static final Game NO_GAME = new Game();

    @Override
    protected Game transformPayload(ByteArrayInputStream item) throws Exception {
        Game game = new PGNReader(item, "").parseGame();
        return game != null ? game : NO_GAME;
    }
}
