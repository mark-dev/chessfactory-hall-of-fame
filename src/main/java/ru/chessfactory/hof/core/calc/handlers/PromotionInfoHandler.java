package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.hof.commons.AggregateResultField;
import ru.chessfactory.hof.commons.IMoveHandler;

public class PromotionInfoHandler implements IMoveHandler {
    @AggregateResultField
    private int promotionCount;

    @Override
    public void handleMove(Position p, Move m) {
        if (m.isPromotion()) {
            promotionCount++;
        }
    }
}
