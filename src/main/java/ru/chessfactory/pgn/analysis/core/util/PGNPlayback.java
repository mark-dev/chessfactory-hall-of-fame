package ru.chessfactory.pgn.analysis.core.util;

import chesspresso.game.Game;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PGNPlayback {
    protected Game game;
    protected Collection<IMoveHandler> handlers;

    public PGNPlayback(Game game, Collection<IMoveHandler> handlers) {
        this.game = game;
        this.handlers = handlers;
    }


    public Game getGame() {
        return game;
    }

    public Map<Class, Map<String, Object>> getStatistics() {


        //Проигрываем партию, передаем ходы в обработчики
        game.gotoStart();
        while (game.hasNextMove()) {
            handlers.forEach(h -> h.handleMove(game.getPosition(), game.getNextMove()));
            game.goForward();
        }

        //Собираем результаты

        Map<Class, Map<String, Object>> resultMap = new HashMap<>(handlers.size());
        handlers.forEach(h -> {
            resultMap.put(h.getClass(), h.result());
        });

        return resultMap;
    }
}
