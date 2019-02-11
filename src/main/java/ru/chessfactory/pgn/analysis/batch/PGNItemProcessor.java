package ru.chessfactory.pgn.analysis.batch;

import chesspresso.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import ru.chessfactory.pgn.analysis.calc.IMoveHandler;
import ru.chessfactory.pgn.analysis.calc.handlers.GameStatHandler;
import ru.chessfactory.pgn.analysis.calc.handlers.PieceStatisticsHandler;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;
import ru.chessfactory.pgn.analysis.read.PGNTokenEscapeReader;
import ru.chessfactory.pgn.analysis.util.PGNPlayback;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PGNItemProcessor implements ItemProcessor<ByteArrayInputStream, GameAggregates> {

    @Override
    public GameAggregates process(ByteArrayInputStream item) throws Exception {

        Game game = new PGNTokenEscapeReader(item, "").parseGame();
        //Такое может быть, если нет ходов например просто написанно 1-0 в теле PGN
        if (game != null) {
            return handleGame(game);
        }
        return null;
    }

    private GameAggregates handleGame(Game game) {
        List<IMoveHandler> handlers = Arrays.asList(new PieceStatisticsHandler(), new GameStatHandler());
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

        return ga;
    }
}
