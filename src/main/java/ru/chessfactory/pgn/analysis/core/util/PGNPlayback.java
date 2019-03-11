package ru.chessfactory.pgn.analysis.core.util;

import chesspresso.game.Game;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.core.GameAggregates;
import ru.chessfactory.pgn.analysis.core.calc.AggregateResultField;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;


@Slf4j
public class PGNPlayback {
    protected Game game;
    protected Collection<IMoveHandler> handlers;

    public PGNPlayback(Game game, Collection<IMoveHandler> handlers) {
        this.game = game;
        this.handlers = handlers;
    }

    @SneakyThrows
    public GameAggregates collectAggregates() {

        //Prepare handlers
        handlers.forEach(h -> h.prepare(game));

        game.gotoStart(); //required, by default last move set on board.

        //Iterate though moves, notify handlers.
        //TODO: Exception handling?
        while (game.hasNextMove()) {
            handlers.forEach(h -> h.handleMove(game.getPosition(), game.getNextMove()));
            game.goForward();
        }

        return collectResults();
    }

    @SneakyThrows
    private GameAggregates collectResults() {
        GameAggregates result = new GameAggregates();

        for (IMoveHandler h : handlers) {
            h.beforeCollect();

            //Each handler: Lookup for @AggregateResultField and treat field value as result.

            for (Field field : h.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                Annotation[] annotations = field.getDeclaredAnnotationsByType(AggregateResultField.class);
                if (annotations.length > 0) {
                    AggregateResultField af = (AggregateResultField) annotations[0];
                    String name = field.getName();
                    Object value = field.get(h);

                    result.put(name, value);
                }
            }
        }
        return result;
    }

}
