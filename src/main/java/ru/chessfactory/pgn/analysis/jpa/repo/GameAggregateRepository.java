package ru.chessfactory.pgn.analysis.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chessfactory.pgn.analysis.jpa.entity.GameAggregates;

public interface GameAggregateRepository extends JpaRepository<GameAggregates, Long> {
}
