package ru.chessfactory.hof.core;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.HashMap;

//For faster hashcode (uses in aggregation hashmap)
@EqualsAndHashCode(of = "url")
public class GameAggregates extends HashMap<String, Object> {
    private String url;

    public GameAggregates(@NonNull String gameUrl) {
        this.url = gameUrl;
        put("url", gameUrl);
    }
}
