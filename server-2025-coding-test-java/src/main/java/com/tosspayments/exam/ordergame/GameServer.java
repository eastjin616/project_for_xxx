package com.tosspayments.exam.ordergame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GameServer {
    private final int maxUserCount;
    private final ServerSocket serverSocket;
    private final AtomicReference<Boolean> running = new AtomicReference<>(true);
    private final List<Socket> clients = new ArrayList<>();

    public GameServer(final int maxUserCount, final int port) {
        this.maxUserCount = maxUserCount;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        new Thread (() -> {
            try {
                while (running.get() && clients.size() < maxUserCount) {
                    final Socket clientSocket = serverSocket.accept();
                    clients.add(clientSocket);
                }
            } catch (final SocketException e) {
                // 서버 종료 과정에서 발생할 수 있는 정상적인 예외
                if (running.get()) {
                    // 예외가 stop() 호출 때문이 아니라면 로그로 출력
                    e.printStackTrace();
                }
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }

            new GameLogic(clients).start();
        }).start();
    }

    public void stop() {
        running.set(false);
        try {
            for (final Socket client : clients) {
                client.close();
            }
            serverSocket.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
