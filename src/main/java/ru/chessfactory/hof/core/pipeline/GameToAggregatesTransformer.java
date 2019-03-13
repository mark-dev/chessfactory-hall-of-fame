package ru.chessfactory.hof.core.pipeline;

import chesspresso.game.Game;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.StopWatch;
import ru.chessfactory.hof.core.GameAggregates;
import ru.chessfactory.hof.core.GameTypes;
import ru.chessfactory.hof.core.calc.IMoveHandler;
import ru.chessfactory.hof.core.util.PGNPlayback;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GameToAggregatesTransformer extends AbstractTransformer {

    private static final Pattern TIMECONTROL_HEADER_REGEXP_PATTERN = Pattern.compile("(\\d+)\\+(\\d+)");
    private static final DateTimeFormatter PGN_HEADER_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");


    private Collection<Class<? extends IMoveHandler>> handlerClasses;

    public GameToAggregatesTransformer(MeterRegistry registry) {
        //TODO: package to config
        Reflections reflections = new Reflections("ru.chessfactory.hof.core.calc.handlers");
        handlerClasses = reflections.getSubTypesOf(IMoveHandler.class);
    }

    @Override
    @Timed(value = "chessfactory.pipeline.aggcalc",
            histogram = true,
            percentiles = {0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.99})
    public GameAggregates doTransform(Message<?> item) throws Exception {
        return handleGame((Message<Game>) item);
    }


    private GameAggregates handleGame(Message<Game> item) {
        StopWatch sw = new StopWatch();
        sw.start();

        Game game = item.getPayload();
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


        GameAggregates agg = createAggregateWithCommonFields(item);
        playback.collectAggregatesInto(agg);
        sw.stop();
        return agg;
    }


    private GameTypes timeControlToGameType(String timeControlString) {
        Matcher matcher = TIMECONTROL_HEADER_REGEXP_PATTERN.matcher(timeControlString);
        if (matcher.matches()) {
            int secs = Integer.parseInt(matcher.group(1));
            int perMove = Integer.parseInt(matcher.group(2));
            //https://lichess.org/qa/47/how-are-classical-blitz-and-bullet-defined
            double n = (secs / 20.0 + (perMove) * 2) * 0.12;
            if (n <= 1.05)
                return GameTypes.BULLET;
            else if (n > 1.05 && n <= 2.85)
                return GameTypes.BLITZ;
            else if (n > 2.85 && n <= 8.95)
                return GameTypes.RAPID;
            else if (n > 8.95)
                return GameTypes.CLASSICAL;
            else
                return GameTypes.OTHER;
        } else {
            return GameTypes.OTHER;
        }

    }

    private Integer safeReadInteger(Game game, String tagName, int defaultValue) {
        try {
            return Integer.parseInt(game.getTag(tagName));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    private String safeReadString(Game game, String tagName, String defaultValue) {
        String tagValue = game.getTag(tagName);
        return tagValue != null ? tagValue : defaultValue;
    }

    private GameAggregates createAggregateWithCommonFields(Message<Game> item) {

        Game game = item.getPayload();
        MessageHeaders headers = item.getHeaders();
        String gameUrl = game.getTag("Site");

        GameAggregates agg = new GameAggregates(gameUrl);
        Integer blackElo = safeReadInteger(game, "WhiteElo", 0);
        Integer whiteElo = safeReadInteger(game, "BlackElo", 0);

        GameTypes gameType = timeControlToGameType(game.getTag("TimeControl"));

        agg.put("blackElo", blackElo);
        agg.put("whiteElo", whiteElo);
        agg.put("gameType", gameType.ordinal());
        agg.put("utc", extractGameUtc(game));
        agg.put("plies", game.getNumOfPlies());
        agg.put("fileName", headers.get(FileHeaders.FILENAME));
        agg.put("whitePlayer", safeReadString(game, "White", ""));
        agg.put("blackPlayer", safeReadString(game, "Black", ""));

        return agg;
    }

    private Time extractGameUtc(Game game) {
        String utcDate = game.getTag("UTCDate");
        String utcTime = game.getTag("UTCTime");

        TemporalAccessor parse = PGN_HEADER_DATETIME_FORMATTER.parse(utcDate + " " + utcTime);

        LocalDateTime ldt = LocalDateTime.from(parse);
        return new Time(ldt.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
