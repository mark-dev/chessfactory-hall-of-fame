package ru.chessfactory.pgn.analysis.test;

import chesspresso.game.Game;
import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.calc.handlers.GameStatHandler;
import ru.chessfactory.pgn.analysis.calc.IMoveHandler;
import ru.chessfactory.pgn.analysis.calc.PieceInformation;
import ru.chessfactory.pgn.analysis.calc.handlers.PieceStatisticsHandler;
import ru.chessfactory.pgn.analysis.read.LichessPNGScanner;
import ru.chessfactory.pgn.analysis.util.PGNPlayback;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class SimpleDebugPNGScanner extends LichessPNGScanner {
    String mostFragsGame = null;
    PieceInformation mvp = null;

    String mostRangedGame = null;
    double maxRangedDst = 0.0;

    public SimpleDebugPNGScanner(File database) {
        super(database);
    }

    @Override
    protected void handleGame(Game game) {
        List<IMoveHandler> handlers = Arrays.asList(new PieceStatisticsHandler(), new GameStatHandler());
        PGNPlayback playback = new PGNPlayback(game, handlers);
        String gameUrl = game.getTag("Site");
        Map<Class, Map<String, Object>> statistics = playback.getStatistics();


        PieceInformation thisGameMvp = (PieceInformation) statistics.get(PieceStatisticsHandler.class).get("mvp");
        log.info("{} \t {}", gameUrl, statistics);
        if (thisGameMvp != null && (mvp == null || mvp.getFrags() < thisGameMvp.getFrags())) {
            mvp = thisGameMvp;
            mostFragsGame = gameUrl;
        }

        Double thisGameRangedDst = (Double) statistics.get(GameStatHandler.class).get("avgRangedCaptureDistance");
        if (!thisGameRangedDst.isNaN() && thisGameRangedDst > maxRangedDst) {
            maxRangedDst = thisGameRangedDst;
            mostRangedGame = gameUrl;
        }
    }
}
