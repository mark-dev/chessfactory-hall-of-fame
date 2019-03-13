package ru.chessfactory.hof.core.util;

import chesspresso.Chess;

public class ChessUtils {
    private static final int QUEEN_MATERIAL = pieceToMaterial(Chess.QUEEN);

    public static int pieceToMaterial(int piece) {
        //By default king = queen, but in some cases(re-captures) logic can be different
        return pieceToMaterial(piece, QUEEN_MATERIAL);
    }

    public static int pieceToMaterial(int piece, int kingMaterial) {
        switch (piece) {
            case Chess.PAWN:
                return 100;
            case Chess.KNIGHT:
                return 300;
            case Chess.BISHOP:
                return 325;
            case Chess.ROOK:
                return 500;
            case Chess.KING:
                return kingMaterial;
            case Chess.QUEEN:
                return 900;
            default:
                return 0;
        }
    }
}
