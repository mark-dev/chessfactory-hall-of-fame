package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.SneakyThrows;
import ru.chessfactory.hof.core.calc.AggregateResultField;
import ru.chessfactory.hof.core.calc.IMoveHandler;

public class EnPassantInfoHandler implements IMoveHandler {
    @AggregateResultField
    private int epMoves = 0;

    @AggregateResultField
    private boolean epMate = false;

    @Override
    @SneakyThrows
    public void handleMove(Position p, Move m) {
        if (Move.isEPMove(m.getShortMoveDesc())) {
            epMoves++;
            if (m.isMate()) {
                //TODO: Drop if this is discovered mate
                epMate = true;
            }
        }
    }


}
