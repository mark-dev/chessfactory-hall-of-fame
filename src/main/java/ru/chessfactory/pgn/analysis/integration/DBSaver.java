package ru.chessfactory.pgn.analysis.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.StopWatch;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;
import ru.chessfactory.pgn.analysis.jpa.repo.GameAggregateRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DBSaver implements GenericHandler<Collection<Message<GameAggregates>>> {
    @Autowired
    private GameAggregateRepository repository;

    private int saved = 0;

    @Override
    public Object handle(Collection<Message<GameAggregates>> payload, MessageHeaders headers) {
        StopWatch sw = new StopWatch();

        List<GameAggregates> aggs = payload.stream()
                .map(Message::getPayload)
                .collect(Collectors.toList());

        sw.start();
        repository.saveAll(aggs);
        saved += aggs.size();
        sw.stop();
        log.info("Game aggregates batch saved, takes: {}. Total saved = {}", sw.getLastTaskTimeMillis(), saved);
        return null;
    }
}
