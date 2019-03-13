package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.SneakyThrows;
import ru.chessfactory.hof.core.calc.AggregateResultField;
import ru.chessfactory.hof.core.calc.IMoveHandler;

public class MateInfoHandler implements IMoveHandler {
    @AggregateResultField
    private int materialFromMatedSideView = 0;

    @AggregateResultField
    private String matePiece = "?";

    @AggregateResultField
    private Boolean mateByPromotion = false;

    @Override
    @SneakyThrows
    public void handleMove(Position p, Move m) {
        boolean mate = m.isMate();
        if (mate) {
            p.doMove(m);
            materialFromMatedSideView = p.getMaterial();
            p.undoMove();

            //TODO: handle discovered mate! MatePiece should be real king attacker
            int matePieceAsInt = m.getMovingPiece();
            if (m.isPromotion()) {
                matePieceAsInt = m.getPromo();
                mateByPromotion = true;
            }
            matePiece = String.valueOf(Chess.pieceToChar(matePieceAsInt));
        }

    }

}
