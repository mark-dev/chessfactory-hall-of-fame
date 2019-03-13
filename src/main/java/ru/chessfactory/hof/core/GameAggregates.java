package ru.chessfactory.hof.core;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Objects;

public class GameAggregates extends HashMap<String, Object> {
    private String url;

    public GameAggregates(@NonNull String gameUrl) {
        this.url = gameUrl;
        put("url", gameUrl);
    }

    //For faster hashcode (uses in aggregation hashmap)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameAggregates that = (GameAggregates) o;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), url);
    }
}
