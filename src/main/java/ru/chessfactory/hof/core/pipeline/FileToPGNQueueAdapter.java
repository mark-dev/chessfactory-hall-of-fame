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

@Slf4j
public class FileToPGNQueueAdapter implements GenericHandler<File> {

    private IntegrationGateway gateway;
    private long readLimit;
    private long readed = 0;

    public FileToPGNQueueAdapter(IntegrationGateway gateway, long readLimit) {
        this.gateway = gateway;
        this.readLimit = readLimit;
    }

    @Override
    public Object handle(File payload, MessageHeaders headers) {
        if (readLimitNotReached()) {
            try (BZipPNGReader producer = BZipPNGReader.of(payload, readLimitForDelegate())) {
                ByteArrayInputStream pgn = null;
                while ((pgn = producer.readNextGame()) != null) {
                    GenericMessage<ByteArrayInputStream> msg = new GenericMessage<>(pgn, headers);
                    gateway.produce(msg);
                    readed++;
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
            return readLimit > readed;
    }

    private long readLimitForDelegate() {
        if (readLimit < 0)
            return readLimit;
        if (readLimit > readed) {
            return readLimit - readed;
        } else
            return 0;
    }
}
