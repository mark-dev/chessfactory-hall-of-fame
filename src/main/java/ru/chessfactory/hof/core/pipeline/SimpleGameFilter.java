package ru.chessfactory.hof.core.pipeline;

import chesspresso.game.Game;
import org.springframework.integration.core.GenericSelector;

public class SimpleGameFilter implements GenericSelector<Game> {
    @Override
    public boolean accept(Game g) {
        //Some games have not been started. Drop this games.
        return g != PGNToGameTransformator.NO_GAME;
    }
}
