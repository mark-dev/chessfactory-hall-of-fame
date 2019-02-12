package ru.chessfactory.pgn.analysis.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;

import java.util.List;

public interface GameAggregateRepository extends JpaRepository<GameAggregates, Long> {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    <S extends GameAggregates> List<S> saveAll(Iterable<S> entities);
}
