package ru.chessfactory.hof.core.util;

import chesspresso.game.Game;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.hof.commons.GameAggregates;
import ru.chessfactory.hof.commons.AggregateResultField;
import ru.chessfactory.hof.commons.IMoveHandler;

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
    public void collectAggregatesInto(GameAggregates agg) {

        //Prepare handlers
        handlers.forEach(h -> h.prepare(game));

        game.gotoStart(); //this is required, by default last move set on board.

        //Iterate though moves, notify handlers.
        //TODO: Exception handling?
        while (game.hasNextMove()) {
            handlers.forEach(h -> h.handleMove(game.getPosition(), game.getNextMove()));
            game.goForward();
        }

        collectResultsInto(agg);
    }

    @SneakyThrows
    private void collectResultsInto(GameAggregates result) {

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
    }

}
