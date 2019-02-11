package ru.chessfactory.pgn.analysis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "game_aggregates")
@Data
@EqualsAndHashCode
@TypeDef(name = "JsonType", defaultForType = Map.class, typeClass = MapJsonbUserType.class)
public class GameAggregates {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aggs")
    private Map<String, Object> aggs;

    @Column(name = "url")
    private String url;

    @Column(name = "black_elo")
    private Integer blackElo;

    @Column(name = "white_elo")
    private Integer whiteElo;
}
