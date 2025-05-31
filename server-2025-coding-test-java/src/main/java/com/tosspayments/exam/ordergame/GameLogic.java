package com.tosspayments.exam.ordergame;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class GameLogic {
    private final List<Socket> clients;

    public GameLogic(List<Socket> clients) {
        this.clients = clients;
    }

    public void start() {
        try {
            for (Socket client : clients) {
                send(client, "시작");
            }

            Set<Socket> alreadyPlayed = new HashSet<>();
            Set<Integer> usedNumbers = new HashSet<>();
            int expectedNumber = 1;

            while (alreadyPlayed.size() < clients.size()) {
                for (Socket client : clients) {
                    if (alreadyPlayed.contains(client)) continue;

                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                    if (client.getInputStream().available() == 0) {
                        continue;
                    }

                    String input = in.readLine();
                    if (input == null) {
                        out.println("실패");
                        alreadyPlayed.add(client);
                        continue;
                    }

                    int num;
                    try {
                        num = Integer.parseInt(input.trim());
                    } catch (NumberFormatException e) {
                        out.println("실패");
                        alreadyPlayed.add(client);
                        continue;
                    }

                    if (usedNumbers.contains(num)) {
                        out.println("실패");
                    } else if (num == expectedNumber) {
                        out.println("성공");
                        usedNumbers.add(num);
                        expectedNumber++;
                    } else {
                        out.println("실패");
                        usedNumbers.add(num);
                    }

                    alreadyPlayed.add(client);
                }
            }

            for (Socket client : clients) {
                send(client, "게임종료");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(Socket client, String msg) throws IOException {
        new PrintWriter(client.getOutputStream(), true).println(msg);
    }
}
