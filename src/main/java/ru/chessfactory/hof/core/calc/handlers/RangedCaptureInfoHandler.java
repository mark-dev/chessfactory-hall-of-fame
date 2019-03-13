package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.hof.core.calc.AggregateResultField;
import ru.chessfactory.hof.core.calc.IMoveHandler;

import static chesspresso.Chess.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RangedCaptureInfoHandler implements IMoveHandler {
    @AggregateResultField
    private int rangedCaptureMoves = 0;

    @AggregateResultField
    private Double avgRangedCaptureDistance = 0.0;

    private double rangedCaptureDistance = 0.0;

    @Override
    public void handleMove(Position p, Move m) {
        if (m.isCapturing() && (m.getMovingPiece() == BISHOP || m.getMovingPiece() == ROOK || m.getMovingPiece() == QUEEN)) {
            double moveDistance = moveDistance(m);
            rangedCaptureDistance += moveDistance;
            rangedCaptureMoves++;
        }
    }

    @Override
    public void beforeCollect() {
        avgRangedCaptureDistance = rangedCaptureDistance / rangedCaptureMoves;
        if (avgRangedCaptureDistance.isNaN()) {
            avgRangedCaptureDistance = 0.0;
        }
    }

    private double moveDistance(Move m) {
        return sqrt(pow(deltaCol(m.getFromSqi(), m.getToSqi()), 2) + pow(deltaRow(m.getFromSqi(), m.getToSqi()), 2));
    }
}
