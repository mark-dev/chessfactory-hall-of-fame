package ru.chessfactory.pgn.analysis.core.misc;

import lombok.extern.slf4j.Slf4j;
import ru.chessfactory.pgn.analysis.cfg.integration.IntegrationGateway;
import ru.chessfactory.pgn.analysis.core.util.BasePGNProducer;

import java.io.ByteArrayInputStream;

@Slf4j
public class PGNGatewayProducer {

    private IntegrationGateway gateway;
    private BasePGNProducer delegate;

    public PGNGatewayProducer(String fileName, Long readLimit, IntegrationGateway gateway) {
        delegate = BasePGNProducer.of(fileName, readLimit);
        this.gateway = gateway;
    }


    public void produceAll() {
        ByteArrayInputStream pgn = null;
        while ((pgn = delegate.readNextGame()) != null) {
            gateway.produce(pgn);
        }
    }
}
