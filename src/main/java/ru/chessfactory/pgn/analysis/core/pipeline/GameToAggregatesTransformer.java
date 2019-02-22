package ru.chessfactory.pgn.analysis.core.pipeline;

import chesspresso.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.util.StopWatch;
import ru.chessfactory.pgn.analysis.core.calc.IMoveHandler;
import ru.chessfactory.pgn.analysis.core.util.PGNPlayback;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;

import java.util.*;

@Slf4j
public class GameToAggregatesTransformer extends AbstractPayloadTransformer<Game, GameAggregates> {

    private Collection<Class<? extends IMoveHandler>> handlerClasses;

    public GameToAggregatesTransformer() {
        //TODO: package to config
        Reflections reflections = new Reflections("ru.chessfactory.pgn.analysis.core.calc.handlers");
        handlerClasses = reflections.getSubTypesOf(IMoveHandler.class);

    }

    @Override
    public GameAggregates transformPayload(Game item) throws Exception {
        return handleGame(item);
    }


    private GameAggregates handleGame(Game game) {
        StopWatch sw = new StopWatch();
        sw.start();

        List<IMoveHandler> handlers = new ArrayList<>(handlerClasses.size());
        for (Class<? extends IMoveHandler> c : handlerClasses) {
            try {
                IMoveHandler h = c.newInstance();
                handlers.add(h);
            } catch (Exception e) {
                log.error("Failed to create handler of clazz: {}. Skip it", c);
            }
        }
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
