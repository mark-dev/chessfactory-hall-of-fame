package ru.chessfactory.pgn.analysis.core.calc;

import chesspresso.Chess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceInformation {
    private int initialSqi;
    private int moveCount;
    private int piece;
    private int frags;

    @Override
    public String toString() {
        return "PieceInformation{" +
                "initialSqi=" + Chess.sqiToStr(initialSqi) +
                ", moveCount=" + moveCount +
                ", piece=" + Chess.pieceToChar(piece) +
                ", frags=" + frags +
                '}';
    }
}
