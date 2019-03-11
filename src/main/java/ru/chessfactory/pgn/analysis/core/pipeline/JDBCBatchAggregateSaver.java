package ru.chessfactory.pgn.analysis.core.pipeline;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.StopWatch;
import ru.chessfactory.pgn.analysis.core.GameAggregates;

import javax.annotation.PostConstruct;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class JDBCBatchAggregateSaver implements GenericHandler<Collection<Message<GameAggregates>>> {
    private static final String TABLE_NAME = "game_aggregates";
    @Autowired
    private JdbcTemplate template;

    private List<String> tableColumns;
    private String insertQuery;


    private AtomicLong prevInvocationMillis = new AtomicLong();

    private Counter totalSavedCounter;

    public JDBCBatchAggregateSaver(MeterRegistry meterRegistry) {
        totalSavedCounter = meterRegistry.counter("chessfactory.pipeline.dbsave.total");
    }

    @PostConstruct
    @SneakyThrows
    public void init() {
        tableColumns = obtainColumnList();
        insertQuery = buildInsertQuery(tableColumns);
        log.info("GameAggregates insert query: {}", insertQuery);
    }

    @Override
    @Timed(value = "chessfactory.pipeline.dbsave",
            histogram = true,
            percentiles = {0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.99})
    public Object handle(Collection<Message<GameAggregates>> payload, MessageHeaders headers) {
        StopWatch sw = new StopWatch();

        List<GameAggregates> aggs = payload.stream()
                .map(Message::getPayload)
                .collect(Collectors.toList());

        sw.start();
        save(aggs);
        sw.stop();


        long prevInvoke = prevInvocationMillis.get();
        long now = System.currentTimeMillis();
        totalSavedCounter.increment(aggs.size());


        log.info("Game aggregates batch saved, takes: {} ms. Total saved = {}. PrevInvokeDelta = {}",
                sw.getLastTaskTimeMillis(), (long) totalSavedCounter.count(), now - prevInvoke);

        prevInvocationMillis.set(now);

        return null;
    }


    @SneakyThrows
    protected void save(List<GameAggregates> aggs) {
        List<Object[]> args = aggs
                .stream()
                .map(this::aggregatesToRow)
                .collect(Collectors.toList());
        template.batchUpdate(insertQuery, args);
    }

    private Object[] aggregatesToRow(Map<String, Object> a) {
        Object[] row = new Object[tableColumns.size()];
        int idx = 0;
        for (String dc : tableColumns) {
            row[idx] = a.get(dc);
            idx++;
        }
        return row;
    }


    private String buildInsertQuery(List<String> tableColumns) {
        //TODO: replate to string concat? Called one time..
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(TABLE_NAME).append("(");
        sb.append(String.join(",", tableColumns));
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        String params = IntStream.range(0, tableColumns.size()).mapToObj(any -> "?").collect(Collectors.joining(","));
        sb.append(params);
        sb.append(")");
        return sb.toString();
    }

    @SneakyThrows
    private List<String> obtainColumnList() {
        DatabaseMetaData metadata = template.getDataSource().getConnection().getMetaData();

        ResultSet resultSet =
                metadata.getColumns(null, null, TABLE_NAME, null);
        List<String> columns = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("COLUMN_NAME");
            columns.add(name);
        }
        return columns;
    }
}
