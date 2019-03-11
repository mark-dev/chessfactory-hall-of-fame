package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.pgn.analysis.core.calc.AggregateResultField;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

public class PiecesInColumnInfoHandler implements IMoveHandler {

    @AggregateResultField
    private int allPiecesInColumnPly = 0;

    @AggregateResultField
    private int allPiecesInColumnCol = 0;

    @Override
    public void handleMove(Position p, Move m) {
        int col = Chess.sqiToCol(m.getToSqi());

        //TODO: Calculate material of all column, this is can be interesting.
        // - eg: if material low, there is probably many pawns on column
        boolean hasColumnPiecePosition = true;
        for (int r = 0; r < 8; r++) {
            if (p.getPiece(Chess.coorToSqi(col, r)) == Chess.NO_PIECE) {
                hasColumnPiecePosition = false;
                break;
            }
        }
        if (hasColumnPiecePosition) {
            allPiecesInColumnPly = p.getPlyNumber();
            allPiecesInColumnCol = col;
        }
    }
}
