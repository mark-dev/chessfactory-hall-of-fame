package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.hof.core.calc.AggregateResultField;
import ru.chessfactory.hof.core.calc.IMoveHandler;
import ru.chessfactory.hof.core.util.ChessUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TopKnightForkHandler implements IMoveHandler {
    @AggregateResultField
    private List<String> maxForkMaterialAmountPieces = Collections.emptyList();

    @AggregateResultField
    private int maxForkMaterialAmount = 0;
    @AggregateResultField
    private int maxForkMaterialAmountPly = 0;

    private List<Integer> maxForkMaterialAmountPiecesAstInt = Collections.emptyList();
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

            List<Integer> thisForkPieces = new ArrayList<>(7);  //7 - max fork value
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
                    int targetPieceMaterial = ChessUtils.pieceToMaterial(targetPiece);

                    thisForkMaterialAmount += targetPieceMaterial;
                    thisForkSize++;
                    thisForkPieces.add(targetPiece);
                }
            }
            boolean hasForks = thisForkSize > 1;
            if (hasForks) {
                int thisMovePly = p.getPlyNumber();
                log.debug("Knight move to {} is fork to {} pieces, material = {}. MoveNumber = {}",
                        Chess.sqiToStr(knightMoveToSQI), thisForkSize, thisForkMaterialAmount, thisMovePly);

                //Assert new max values(if necessary)
                if (thisForkMaterialAmount > maxForkMaterialAmount) {
                    maxForkMaterialAmount = thisForkMaterialAmount;
                    maxForkMaterialAmountPly = thisMovePly;
                    maxForkMaterialAmountPiecesAstInt = thisForkPieces;
                }
            }
            //Other handlers do not interesting in our internal-need position changes.
            p.undoMove();
        }

    }


    @Override
    public void beforeCollect() {
        maxForkMaterialAmountPieces = maxForkMaterialAmountPiecesAstInt
                .stream()
                .map(Chess::pieceToChar)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
