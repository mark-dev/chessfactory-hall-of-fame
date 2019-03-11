package ru.chessfactory.pgn.analysis.core.calc;

import chesspresso.game.Game;
import chesspresso.move.Move;
import chesspresso.position.Position;

public interface IMoveHandler {
    /**
     * @param p Current position
     * @param m move, played in position p
     */
    void handleMove(Position p, Move m);

    /*
    * Called before first handleMove call.
    * Handler can use some headers from game if needed
    * */
    default void prepare(Game game) {
    }

    /*
    * Can be use for prepare result.
    * Called after all moves.
    * */
    default void beforeCollect() {
    }

}
