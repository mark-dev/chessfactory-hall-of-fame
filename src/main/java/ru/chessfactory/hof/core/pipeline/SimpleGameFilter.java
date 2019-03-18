package ru.chessfactory.hof.core.pipeline;

import chesspresso.game.Game;
import org.springframework.integration.core.GenericSelector;

public class SimpleGameFilter implements GenericSelector<Game> {
    @Override
    public boolean accept(Game g) {
        //Some games have not been started. Drop this games.
        //You can extend this filter logic, if you want drop some games (for example by player rating)
        return g != PGNToGameTransformator.NO_GAME;
    }
}
