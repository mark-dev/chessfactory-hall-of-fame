package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.pgn.analysis.core.calc.AggregateResultField;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

public class CaptureSharpnessInfoHandler implements IMoveHandler {

    @AggregateResultField
    private int maxCaptureSharpness = 0;
    @AggregateResultField
    private int maxCaptureSharpnessPly = 0;

    @Override
    public void handleMove(Position p, Move m) {
        short[] captureMovesThisPos = p.getAllCapturingMoves();
        if (captureMovesThisPos.length > maxCaptureSharpness) {
            maxCaptureSharpness = captureMovesThisPos.length;
            maxCaptureSharpnessPly = p.getPlyNumber();
        }
    }


}
