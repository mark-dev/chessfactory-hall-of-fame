package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.SneakyThrows;
import ru.chessfactory.pgn.analysis.core.calc.AggregateResultField;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

public class MateWithMaterialInfoHandler implements IMoveHandler {
    @AggregateResultField
    private int materialFromMatedSideView = 0;


    @Override
    @SneakyThrows
    public void handleMove(Position p, Move m) {
        boolean mate = m.isMate();
        if (mate) {
            p.doMove(m);
            materialFromMatedSideView = p.getMaterial();
            p.undoMove();
        }
    }
}
