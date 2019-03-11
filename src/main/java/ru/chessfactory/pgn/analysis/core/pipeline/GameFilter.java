package ru.chessfactory.pgn.analysis.core.pipeline;

import chesspresso.game.Game;
import org.springframework.integration.core.GenericSelector;

public class GameFilter implements GenericSelector<Game> {
    @Override
    public boolean accept(Game g) {
        //Some games have not been started. Drop this games.
        return g != PGNToGameTransformator.NO_GAME;
    }
}
