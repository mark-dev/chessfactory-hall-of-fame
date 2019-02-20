package ru.chessfactory.pgn.analysis.core.pipeline;

import org.springframework.integration.endpoint.AbstractMessageSource;
import ru.chessfactory.pgn.analysis.core.util.BasePGNProducer;

import java.io.ByteArrayInputStream;

public class PGNMessageSource extends AbstractMessageSource<ByteArrayInputStream> {
    //TODO: Read all pgn files from directory
    private BasePGNProducer delegate;

    public PGNMessageSource(String fileName, Long readLimit) {
        delegate = BasePGNProducer.of(fileName, readLimit);
    }

    @Override
    protected Object doReceive() {
        return delegate.readNextGame();
    }

    @Override
    public String getComponentType() {
        return "inbound-channel-adapter";
    }
}
