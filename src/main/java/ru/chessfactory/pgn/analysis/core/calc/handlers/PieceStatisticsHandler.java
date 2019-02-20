package ru.chessfactory.pgn.analysis.core.calc.handlers;

import chesspresso.Chess;
import chesspresso.move.Move;
import chesspresso.position.Position;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;
import ru.chessfactory.pgn.analysis.core.calc.PieceInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PieceStatisticsHandler implements IMoveHandler {
    private Map<Integer, PieceInformation> chessBoardIndex = new HashMap<>(64);
    private List<PieceInformation> pieces = new ArrayList<>();

    //Если фигура отсутствует, добавляем ее
    private PieceInformation getPieceFromSqi(int sqi, int piece) {
        PieceInformation movingPieceInfo = chessBoardIndex.get(sqi);
        if (movingPieceInfo == null) {
            //У нас не было информации об этой фигуре, создадим ее, на позиции sqi
            movingPieceInfo = new PieceInformation(sqi, 0, piece, 0);
            chessBoardIndex.put(sqi, movingPieceInfo);
            pieces.add(movingPieceInfo);
        }
        return movingPieceInfo;
    }

    @Override
    public void handleMove(Position p, Move m) {
        PieceInformation movingPiece = getPieceFromSqi(m.getFromSqi(), m.getMovingPiece());

        PieceInformation capturedPiece = null;
        //TODO: Еще же есть рокировки, превращения и прочие вещи.
        if (m.isCapturing()) {
            boolean enpassant = Move.isEPMove(m.getShortMoveDesc());

            if (!enpassant) {
                capturedPiece = getPieceFromSqi(m.getToSqi(), p.getPiece(m.getToSqi()));
                chessBoardIndex.remove(m.getToSqi());
            } else {
                //Предыдущий ход должен быть ход пешкой (согласно правилам взятия на проходе)
                Move lastMove = p.getLastMove();
                capturedPiece = getPieceFromSqi(lastMove.getToSqi(), lastMove.getMovingPiece());
                chessBoardIndex.remove(lastMove.getToSqi());
            }
        }

        //Накапливаем какую-то статистику
        movingPiece.setMoveCount(movingPiece.getMoveCount() + 1);
        if (capturedPiece != null) {
            movingPiece.setFrags(movingPiece.getFrags() + 1);
        }
        if (m.isPromotion()) {
            //Делаем из старой фигуры новую
            movingPiece.setPiece(m.getPromo());
        }

        if(m.isCheck()) {

        }
        movePiece(movingPiece, m);

        log.debug("Handle move: m: {}  pos: {}", m, p);
    }

    @Override
    public Map<String, Object> result() {
        PieceInformation mvpPiece = null;
        for (PieceInformation p : pieces) {
            if (mvpPiece == null || mvpPiece.getFrags() < p.getFrags()) {
                mvpPiece = p;
            }
        }

        Map<String, Object> result = new HashMap<>();

        if (mvpPiece != null) {
            result.put("mvpInitialPosition", Chess.sqiToStr(mvpPiece.getInitialSqi()));
            result.put("mvpFrags", mvpPiece.getFrags());
            result.put("mvpType", Chess.pieceToChar(mvpPiece.getPiece()));
        }
        return result;
    }

    private void movePiece(PieceInformation piece, Move m) {
        //Передвигаем фигуру
        chessBoardIndex.remove(m.getFromSqi()); //Этой фигуры больше на этом поле нет
        chessBoardIndex.put(m.getToSqi(), piece); //Она переместилась на другое поле
        //В случае с рокировкой, передвигаем еще и ладью
        boolean isCastle = m.isShortCastle() || m.isLongCastle();
        int rookOldSqi = -1, rookNewSqi = -1;
        if (m.isShortCastle()) {
            if(m.isWhiteMove()) {
                rookOldSqi = Chess.H1;
                rookNewSqi = Chess.F1;
            }
            else {
                rookOldSqi = Chess.H8;
                rookNewSqi = Chess.F8;
            }
        } else if (m.isLongCastle()) {
            if(m.isWhiteMove()) {
                rookOldSqi = Chess.A1;
                rookNewSqi = Chess.D1;
            }
            else {
                rookOldSqi = Chess.A8;
                rookNewSqi = Chess.D8;
            }
        }
        if (isCastle) {
            //TODO: Тут не нужно ли спользовать ENUM BLACK ROOK и WHITE ROOK?
            //Дополнительно перемещаем еще и ладью
            PieceInformation pieceFromSqi = getPieceFromSqi(rookOldSqi, Chess.ROOK);
            chessBoardIndex.put(rookNewSqi, pieceFromSqi);
            chessBoardIndex.remove(rookOldSqi);
        }

    }
}
