package com.tosspayments.exam.ordergame;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameServerLauncher {
    private final int port;
    private final int userCount;
    private GameServer tcpServer;
    private static final Logger log = LoggerFactory.getLogger(GameServerLauncher.class);

    public GameServerLauncher(
        @Value("${gameserver.port}")
        final int port,
        @Value("${gameserver.usercount}")
        final int userCount
    ) {
        this.port = port;
        this.userCount = userCount;
    }

    @PostConstruct
    public void init() {
        tcpServer = new GameServer(userCount, port);
        tcpServer.start();
        log.info("GameServerLauncher started on port {} with max user count {}", port, userCount);
    }

    @PreDestroy
    public void stop() {
        if (tcpServer != null) {
            tcpServer.stop();
        }
    }
}
