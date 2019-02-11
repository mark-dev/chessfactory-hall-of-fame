package ru.chessfactory.pgn.analysis.calc;

import chesspresso.move.Move;

import static chesspresso.Chess.deltaCol;
import static chesspresso.Chess.deltaRow;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utils {
    public static double moveDistance(Move m) {
        double moveDistance = sqrt(pow(deltaCol(m.getFromSqi(), m.getToSqi()), 2) + pow(deltaRow(m.getFromSqi(), m.getToSqi()), 2));
        return moveDistance;
    }
}
