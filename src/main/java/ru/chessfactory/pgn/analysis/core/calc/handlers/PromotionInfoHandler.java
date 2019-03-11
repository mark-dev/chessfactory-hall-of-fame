package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.pgn.analysis.core.calc.AggregateResultField;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

public class PromotionInfoHandler implements IMoveHandler {
    @AggregateResultField
    private int promotionCount;

    @AggregateResultField
    private String mateWithPromotionPieceType = null;

    @Override
    public void handleMove(Position p, Move m) {
        if (m.isPromotion()) {
            promotionCount++;
            if (m.isMate()) {
                mateWithPromotionPieceType = String.valueOf(Chess.pieceToChar(m.getPromo()));
            }
        }
    }
}
