package ru.chessfactory.hof.core.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import ru.chessfactory.hof.cfg.integration.IntegrationGateway;
import ru.chessfactory.hof.core.util.BZipPNGReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Read received file and produce games to pgnQueue
 */
@Slf4j
public class FileToPGNQueueAdapter implements GenericHandler<File> {

    private IntegrationGateway gateway;
    private final Long readLimit;
    private AtomicLong readed = new AtomicLong();

    public FileToPGNQueueAdapter(IntegrationGateway gateway, long readLimit) {
        this.gateway = gateway;
        this.readLimit = readLimit;
    }

    @Override
    public Object handle(File payload, MessageHeaders headers) {
        if (readLimitNotReached()) { //do not open file, if limit already reached
            try (BZipPNGReader producer = BZipPNGReader.of(payload, readLimitForDelegate())) {
                ByteArrayInputStream pgn = null;
                //other thread can reach limit, stop processing if this occurs
                while (readLimitNotReached() && (pgn = producer.readNextGame()) != null) {
                    GenericMessage<ByteArrayInputStream> msg = new GenericMessage<>(pgn, headers);
                    gateway.produce(msg);
                    readed.incrementAndGet();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return payload;
    }

    private boolean readLimitNotReached() {
        if (readLimit < 0)
            return true;
        else
            return readLimit > readed.get();
    }

    private long readLimitForDelegate() {
        if (readLimit < 0)
            return readLimit;
        else {
            synchronized (readLimit) {
                long currentReadedCtx = readed.get();
                if (readLimit > currentReadedCtx) {
                    return readLimit - currentReadedCtx;
                } else
                    return 0;
            }
        }
    }
}
