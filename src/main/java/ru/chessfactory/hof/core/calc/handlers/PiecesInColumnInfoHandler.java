package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import ru.chessfactory.hof.commons.AggregateResultField;
import ru.chessfactory.hof.commons.IMoveHandler;
import ru.chessfactory.hof.commons.ChessUtils;

public class PiecesInColumnInfoHandler implements IMoveHandler {

    @AggregateResultField
    private int allPiecesInColumnPly = 0;

    @AggregateResultField
    private int allPiecesInColumnCol = 0;

    @AggregateResultField
    private int allPiecesInColMaterialValue = 0;

    @Override
    public void handleMove(Position p, Move m) {
        int col = Chess.sqiToCol(m.getToSqi());

        //TODO: Calculate material of all column, this is can be interesting.
        // - eg: if material low, there is probably many pawns on column
        boolean hasColumnPiecePosition = true;
        Integer materialAmount = 0;
        for (int r = 0; r < 8; r++) {
            int piece = p.getPiece(Chess.coorToSqi(col, r));
            if (piece == Chess.NO_PIECE) {
                hasColumnPiecePosition = false;
                break;
            } else {
                materialAmount += ChessUtils.pieceToMaterial(piece);
            }
        }
        //TODO: also keep min material amount(alot of pawns on one column also interesting)
        if (hasColumnPiecePosition && materialAmount > allPiecesInColMaterialValue) {
            allPiecesInColumnPly = p.getPlyNumber();
            allPiecesInColumnCol = col;
            allPiecesInColMaterialValue = materialAmount;
        }
    }
}
