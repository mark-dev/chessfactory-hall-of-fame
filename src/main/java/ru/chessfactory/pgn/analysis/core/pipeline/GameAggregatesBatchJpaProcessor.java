package ru.chessfactory.pgn.analysis.core.pipeline;

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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class GameAggregatesBatchJpaProcessor implements GenericHandler<Collection<Message<GameAggregates>>> {
    @Autowired
    private GameAggregateRepository repository;

    private AtomicInteger saved = new AtomicInteger();

    @Override
    public Object handle(Collection<Message<GameAggregates>> payload, MessageHeaders headers) {
        StopWatch sw = new StopWatch();

        List<GameAggregates> aggs = payload.stream()
                .map(Message::getPayload)
                .collect(Collectors.toList());

        sw.start();
        repository.saveAll(aggs);
        int totalSaved = saved.addAndGet(aggs.size());
        sw.stop();
        log.info("Game aggregates batch saved, takes: {}. Total saved = {}", sw.getLastTaskTimeMillis(), totalSaved);
        return null;
    }
}
