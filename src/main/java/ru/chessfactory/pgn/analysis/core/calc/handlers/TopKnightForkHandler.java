package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.core.calc.AggregateResultField;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

@Slf4j
public class TopKnightForkHandler implements IMoveHandler {
    @AggregateResultField
    private int maxForkSize = 0;
    @AggregateResultField
    private int maxForkMaterialAmount = 0;
    @AggregateResultField
    private int maxForkPly = 0;
    @AggregateResultField
    private int maxForkMaterialAmountPly = 0;

    //TODO: store set<int> - forked pieces, for example we can search forks where few queen are forked

    @Override
    @SneakyThrows
    public void handleMove(Position p, Move m) {
        //TODO: we can drop forks, where knight can be simply recaptured by enemy piece with less or equal material value.
        //TODO: BUT. Maybe this is combination? We need chess-engine to check it.

        if (m.getMovingPiece() == Chess.KNIGHT) {
            //perform Move, for position attack evaluate, based on new position occurs after this move.
            //Undo later
            p.doMove(m);

            int thisForkSize = 0;
            int thisForkMaterialAmount = 0;

            int knightMoveToSQI = m.getToSqi();


            //find All squares, that can be attack from knightMoveToSQI
            for (int sqi = 0; sqi < Chess.NUM_OF_SQUARES; sqi++) {

                int targetPiece = p.getPiece(sqi);
                int pieceColor = p.getColor(sqi);
                boolean isTargetPieceEnemy = targetPiece != Chess.NO_PIECE && pieceColor == p.getToPlay();
                boolean isKnightAttackSquare = p.attacks(knightMoveToSQI, sqi);

                boolean sqiForkCandidate = isKnightAttackSquare && isTargetPieceEnemy;

                if (sqiForkCandidate) {
                    int targetPieceMaterial = pieceToMaterial(targetPiece);
                    thisForkMaterialAmount += targetPieceMaterial;
                    thisForkSize++;
                }
            }
            boolean hasForks = thisForkSize > 1;
            if (hasForks) {
                int thisMovePly = p.getPlyNumber();
                log.debug("Knight move to {} is fork to {} pieces, material = {}. MoveNumber = {}",
                        Chess.sqiToStr(knightMoveToSQI), thisForkSize, thisForkMaterialAmount, thisMovePly);

                //Assert new max values(if necessary)
                if (thisForkSize > maxForkSize) {
                    maxForkSize = thisForkSize;
                    maxForkPly = thisMovePly;
                }
                if (thisForkMaterialAmount > maxForkMaterialAmount) {
                    maxForkMaterialAmount = thisForkMaterialAmount;
                    maxForkMaterialAmountPly = thisMovePly;
                }
            }
            //Other handlers do not interesting in our internal-need position changes.
            p.undoMove();
        }

    }


    private static int pieceToMaterial(int piece) {
        switch (piece) {
            case Chess.PAWN:
                return 100;
            case Chess.KNIGHT:
                return 300;
            case Chess.BISHOP:
                return 325;
            case Chess.ROOK:
                return 500;
            case Chess.KING: //King is important piece for fork calculations.
            case Chess.QUEEN:
                return 900;
            default:
                return 0;
        }
    }
}
