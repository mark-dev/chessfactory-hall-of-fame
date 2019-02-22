package ru.chessfactory.pgn.analysis.core.calc;

import chesspresso.move.Move;
import chesspresso.position.Position;

import java.util.Collections;
import java.util.Map;

public interface IMoveHandler {
    /**
     * @param p Текущая позиция
     * @param m Ход который будет сделан
     */
    void handleMove(Position p, Move m);

    default Map<String, Object> result() {
        return Collections.emptyMap();
    }
}
