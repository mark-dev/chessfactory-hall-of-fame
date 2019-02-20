package ru.chessfactory.pgn.analysis.core.pipeline;

import chesspresso.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.util.StopWatch;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;
import ru.chessfactory.pgn.analysis.core.calc.handlers.GameStatHandler;
import ru.chessfactory.pgn.analysis.core.calc.handlers.PieceStatisticsHandler;
import ru.chessfactory.pgn.analysis.core.util.PGNPlayback;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameToAggregatesTransformer extends AbstractPayloadTransformer<Game, GameAggregates> {

    @Override
    public GameAggregates transformPayload(Game item) throws Exception {
        return handleGame(item);
    }

    protected List<IMoveHandler> gameAggHandlers() {
        return Arrays.asList(new PieceStatisticsHandler(), new GameStatHandler());
    }

    private GameAggregates handleGame(Game game) {
        StopWatch sw = new StopWatch();
        sw.start();

        List<IMoveHandler> handlers = gameAggHandlers();
        PGNPlayback playback = new PGNPlayback(game, handlers);
        String gameUrl = game.getTag("Site");
        log.debug("Game: {}", gameUrl);
        Integer blackElo = Integer.parseInt(game.getTag("WhiteElo"));
        Integer whiteElo = Integer.parseInt(game.getTag("BlackElo"));
        Map<Class, Map<String, Object>> statistics = playback.getStatistics();

        Map<String, Object> collectedStat = new HashMap<>();
        statistics.forEach((k, v) -> {
            collectedStat.putAll(v);
        });

        GameAggregates ga = new GameAggregates();
        ga.setUrl(gameUrl);
        ga.setAggs(collectedStat);
        ga.setBlackElo(blackElo);
        ga.setWhiteElo(whiteElo);

        sw.stop();
        //  log.info("Agg calc takes: {}", sw.getLastTaskTimeMillis());
        return ga;
    }


}
