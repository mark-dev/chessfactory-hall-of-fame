package ru.chessfactory.pgn.analysis.cfg.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.chessfactory.pgn.analysis.cfg.IntegrationConfig;

import java.io.ByteArrayInputStream;

@MessagingGateway
public interface IntegrationGateway {
    @Gateway(requestChannel = IntegrationConfig.PGN_QUEUE_NAME)
    void produce(ByteArrayInputStream bais);
}
