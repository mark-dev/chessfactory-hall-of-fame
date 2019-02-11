package ru.chessfactory.pgn.analysis.calc.handlers;

import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.pgn.analysis.calc.IMoveHandler;

import java.util.HashMap;
import java.util.Map;

import static chesspresso.Chess.*;
import static ru.chessfactory.pgn.analysis.calc.Utils.moveDistance;

public class GameStatHandler implements IMoveHandler {
    private int moves = 0;
    private int epMoves = 0;
    private double rangedCaptureDistance = 0.0;
    private int rangedCaptureMoves = 0;

    @Override
    public void handleMove(Position p, Move m) {
        moves++;
        if (Move.isEPMove(m.getShortMoveDesc())) {
            epMoves++;
        }
        if (m.isCapturing() && (m.getMovingPiece() == BISHOP || m.getMovingPiece() == ROOK || m.getMovingPiece() == QUEEN)) {
            double moveDistance = moveDistance(m);
            rangedCaptureDistance += moveDistance;
            rangedCaptureMoves++;
        }
    }

    @Override
    public Map<String, Object> result() {
        Map<String, Object> res = new HashMap<>();
        res.put("moves", moves);
        res.put("epMoves", epMoves);
        res.put("rangedCaptureMoves", rangedCaptureMoves);
        Double averageRangedCaptureDistance = rangedCaptureDistance / rangedCaptureMoves;
        res.put("avgRangedCaptureDistance", averageRangedCaptureDistance.isNaN() ? 0 : averageRangedCaptureDistance);
        return res;
    }
}
