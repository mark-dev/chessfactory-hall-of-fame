package ru.chessfactory.hof.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.hof.commons.AggregateResultField;
import ru.chessfactory.hof.commons.IMoveHandler;
import ru.chessfactory.hof.core.calc.handlers.dto.PieceInformation;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class PieceStatisticsHandler implements IMoveHandler {
    private Map<Integer, PieceInformation> chessBoardIndex = new HashMap<>(64);
    private List<PieceInformation> pieces = new ArrayList<>();

    @AggregateResultField
    private int mvpFrags = 0;

    @AggregateResultField
    private String mvpInitialPosition;

    @AggregateResultField
    private String mvpType;

    @AggregateResultField
    private int knightLongestPath = 0;

    @AggregateResultField
    private List<String> knightLongestPathDetails = Collections.emptyList();

    @AggregateResultField
    private String longestKnightInitialPosition;


    @Override
    public void handleMove(Position p, Move m) {
        PieceInformation movingPiece = getPieceFromSqi(m.getFromSqi(), m.getMovingPiece());

        PieceInformation capturedPiece = null;
        if (m.isCapturing()) {
            boolean enpassant = Move.isEPMove(m.getShortMoveDesc());

            if (!enpassant) {
                capturedPiece = getPieceFromSqi(m.getToSqi(), p.getPiece(m.getToSqi()));
                chessBoardIndex.remove(m.getToSqi());
            } else {
                //Previous move - is pawn move. (Due rules, else en passant not avaliable)
                Move lastMove = p.getLastMove();
                capturedPiece = getPieceFromSqi(lastMove.getToSqi(), lastMove.getMovingPiece());
                chessBoardIndex.remove(lastMove.getToSqi());
            }
        }

        //Collect some metrics

        movingPiece.setMoveCount(movingPiece.getMoveCount() + 1);
        if (capturedPiece != null) {
            movingPiece.setFrags(movingPiece.getFrags() + 1);
        }
        if (m.isPromotion()) {
            //Change piece type due promotion
            movingPiece.setPiece(m.getPromo());
        }

        if (m.isCheck()) {
            //TODO: Most check-ed piece? This is interesting?
        }

        //keep visitedSQI only for knight
        //(tip: else, if pawn is promoted to knight, we still use pawn's visitedSQI this is incorrect)
        if (movingPiece.getPiece() == Chess.KNIGHT && movingPiece.isKeepKnightTravel()) {
            if (movingPiece.getVisitedSqi().isEmpty()) {
                movingPiece.getVisitedSqi().add(movingPiece.getInitialSqi());
            }
            boolean notVisitedBefore = movingPiece.getVisitedSqi().add(m.getToSqi());
            //If set already contain value, than stop knight travel lookup
            movingPiece.setKeepKnightTravel(notVisitedBefore);
        }

        movePiece(movingPiece, m);
    }


    @Override
    public void beforeCollect() {
        PieceInformation mvpPiece = null;
        PieceInformation longestKnightPath = null;

        for (PieceInformation p : pieces) {
            if (mvpPiece == null || mvpPiece.getFrags() < p.getFrags()) {
                mvpPiece = p;
            }

            if (p.getPiece() == Chess.KNIGHT &&
                    (longestKnightPath == null || (longestKnightPath.getVisitedSqi().size() < p.getVisitedSqi().size()))) {
                longestKnightPath = p;
            }
        }

        if (mvpPiece != null) {
            mvpInitialPosition = Chess.sqiToStr(mvpPiece.getInitialSqi());
            mvpFrags = mvpPiece.getFrags();
            mvpType = String.valueOf(Chess.pieceToChar(mvpPiece.getPiece()));
        }
        if (longestKnightPath != null) {
            longestKnightInitialPosition = Chess.sqiToStr(longestKnightPath.getInitialSqi());
            knightLongestPath = longestKnightPath.getVisitedSqi().size();
            knightLongestPathDetails = longestKnightPath
                    .getVisitedSqi()
                    .stream()
                    .map(Chess::sqiToStr)
                    .collect(Collectors.toList());
        }
    }

    private void movePiece(PieceInformation piece, Move m) {
        //Move piece
        chessBoardIndex.remove(m.getFromSqi());
        chessBoardIndex.put(m.getToSqi(), piece);

        //If castle, also move rook
        boolean isCastle = m.isShortCastle() || m.isLongCastle();
        int rookOldSqi = -1, rookNewSqi = -1;
        if (m.isShortCastle()) {
            if (m.isWhiteMove()) {
                rookOldSqi = Chess.H1;
                rookNewSqi = Chess.F1;
            } else {
                rookOldSqi = Chess.H8;
                rookNewSqi = Chess.F8;
            }
        } else if (m.isLongCastle()) {
            if (m.isWhiteMove()) {
                rookOldSqi = Chess.A1;
                rookNewSqi = Chess.D1;
            } else {
                rookOldSqi = Chess.A8;
                rookNewSqi = Chess.D8;
            }
        }
        if (isCastle) {
            PieceInformation pieceFromSqi = getPieceFromSqi(rookOldSqi, Chess.ROOK);
            chessBoardIndex.put(rookNewSqi, pieceFromSqi);
            chessBoardIndex.remove(rookOldSqi);
        }
    }

    //If piece missing -> add to chessBoardIndex.
    private PieceInformation getPieceFromSqi(int sqi, int piece) {
        PieceInformation movingPieceInfo = chessBoardIndex.get(sqi);
        if (movingPieceInfo == null) {
            //Create piece at position=sqi
            movingPieceInfo = new PieceInformation(sqi, 0, piece, 0, new LinkedHashSet<>(), true);
            chessBoardIndex.put(sqi, movingPieceInfo);
            pieces.add(movingPieceInfo);
        }
        return movingPieceInfo;
    }
}
