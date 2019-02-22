package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;

import java.util.HashMap;
import java.util.Map;

import static chesspresso.move.Move.getFromSqi;
import static chesspresso.move.Move.getToSqi;
import static java.lang.Math.abs;

@Slf4j
public class TopKnightForkHandler implements IMoveHandler {
    private int maxForkSize = 0;
    private int maxForkMaterialAmount = 0;
    private int maxForkMove = 0;

    private static final int KNIGHT_MATERIAL_AMOUNT = pieceToMaterial(Chess.KNIGHT);

    @Override
    @SneakyThrows
    public void handleMove(Position p, Move m) {
        if (m.getMovingPiece() == Chess.KNIGHT) {
            //For position attack evaluate, based on new position occurs after this move. undo later
            p.doMove(m);

            int forkSize = 0;
            int thisForkMaterialAmount = 0;

            int knightMoveToSQI = m.getToSqi();


            //find All squares, that can be attack from this SQI
            for (int sqi = 0; sqi < Chess.NUM_OF_SQUARES; sqi++) {

                int targetPiece = p.getPiece(sqi);
                int pieceColor = p.getColor(sqi);
                boolean targetPieceIsEnemy = targetPiece != Chess.NO_PIECE && pieceColor == p.getToPlay();
                boolean knighAttackSquare = p.attacks(knightMoveToSQI, sqi);


                if (knighAttackSquare && targetPieceIsEnemy) {
                    //TODO: if pawn is unprotected, we should also include it here
                    int targetPieceMaterial = pieceToMaterial(targetPiece);
                    if (targetPieceMaterial > KNIGHT_MATERIAL_AMOUNT || targetPiece == Chess.KING) {
                        thisForkMaterialAmount += targetPieceMaterial;
                        forkSize++;
                    }
                }
            }
            if (forkSize > 1) {
                boolean knightCantBeCapturedWithoutLoosingMaterial = true;
                //Check opposite side moves
                //If knight can be taken without material loosing - this situation are treated as no fork.
                for (short cp : p.getAllCapturingMoves()) {
                    int captureTarget = getToSqi(cp);
                    int movingPiece = p.getPiece(getFromSqi(cp));
                    //Bishop takes is OK, that's why abs delta here
                    boolean materialLoseAfterCapture = knightABSMaterialCompare(pieceToMaterial(movingPiece), 100);
                    if (captureTarget == knightMoveToSQI && !materialLoseAfterCapture) {
                        knightCantBeCapturedWithoutLoosingMaterial = false;
                        break;
                    }
                }

                if (knightCantBeCapturedWithoutLoosingMaterial) {
                    maxForkSize = Integer.max(maxForkSize, forkSize);
                    maxForkMaterialAmount = Integer.max(maxForkMaterialAmount, thisForkMaterialAmount);
                    if (maxForkMaterialAmount != 0 && maxForkMaterialAmount == thisForkMaterialAmount) {
                        maxForkMove = p.getPlyNumber() / 2;
                    }
                    log.debug("Knight move to {} is fork to {} pieces, material = {}. MoveNumber = {}",
                            Chess.sqiToStr(knightMoveToSQI), forkSize, thisForkMaterialAmount, (p.getPlyNumber()) / 2);
                }
            }

            p.undoMove();
        }

    }

    private boolean knightABSMaterialCompare(int pieceMaterial, int delta) {
        return abs(pieceMaterial - KNIGHT_MATERIAL_AMOUNT) > delta;
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
            case Chess.QUEEN:
                return 900;
            default:
                return 0;
        }
    }

    @Override
    public Map<String, Object> result() {
        Map<String, Object> res = new HashMap<>(3);
        res.put("maxForkSize", maxForkSize);
        res.put("maxForkMaterialAmount", maxForkMaterialAmount);
        res.put("maxForkMove", maxForkMove);
        return res;
    }
}
