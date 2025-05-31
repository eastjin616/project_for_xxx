package com.tosspayments.exam.ordergame;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.assertj.core.api.Assertions.assertThat;

/** !!!!!!! 이 테스트를 수행하려면 Disabled를 제거하세요. !!!!!!! */
/** !!!!!!! 제공된 테스트는 과제의 요구사항에 맞게 정상적으로 작성된 테스트입니다. !!!!!!! */
/** /** !!!!!!! 테스트를 자유롭게 추가해주세요. !!!!!!! */
//@Disabled
class GameServerTest {
    private final int port;

    GameServerTest() {
        /** !!!!!!! 사용 가능한 랜덤포트 할당을 위한 코드이니 수정하지 마세요 !!!!!!! */
        try (final ServerSocket socket = new ServerSocket(0)) {
            this.port = socket.getLocalPort();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 유저_4명_중_3번째_사용자가_2를_외쳤다면_실패해야_한다() throws IOException {
        final GameServer server = new GameServer(4, port);
        server.start();

        final TestSocket client1 = new TestSocket("localhost", port);
        final TestSocket client2 = new TestSocket("localhost", port);
        final TestSocket client3 = new TestSocket("localhost", port);
        final TestSocket client4 = new TestSocket("localhost", port);

        assertThat(client1.readLine()).isEqualTo("시작");
        assertThat(client2.readLine()).isEqualTo("시작");
        assertThat(client3.readLine()).isEqualTo("시작");
        assertThat(client4.readLine()).isEqualTo("시작");

        client1.write("1\n");
        assertThat(client1.readLine()).isEqualTo("성공");
        client4.write("2\n");
        assertThat(client4.readLine()).isEqualTo("성공");
        client2.write("2\n");
        assertThat(client2.readLine()).isEqualTo("실패");

        client1.close();
        client2.close();
        client3.close();
        client4.close();
        server.stop();
    }

    @Test
    void 유저_4명_중_4번째_유저가_마지막으로_4를_외쳤다면_마지막_유저는_실패해야_한다() throws IOException {
        final GameServer server = new GameServer(4, port);
        server.start();

        final TestSocket client1 = new TestSocket("localhost", port);
        final TestSocket client2 = new TestSocket("localhost", port);
        final TestSocket client3 = new TestSocket("localhost", port);
        final TestSocket client4 = new TestSocket("localhost", port);

        assertThat(client1.readLine()).isEqualTo("시작");
        assertThat(client2.readLine()).isEqualTo("시작");
        assertThat(client3.readLine()).isEqualTo("시작");
        assertThat(client4.readLine()).isEqualTo("시작");

        client1.write("1\n");
        assertThat(client1.readLine()).isEqualTo("성공");
        client3.write("2\n");
        assertThat(client3.readLine()).isEqualTo("성공");
        client2.write("3\n");
        assertThat(client2.readLine()).isEqualTo("성공");
        client4.write("4\n");
        assertThat(client4.readLine()).isEqualTo("실패");

        client1.close();
        client2.close();
        client3.close();
        client4.close();
        server.stop();
    }

    static class TestSocket {
        private final Socket socket;
        private final BufferedReader reader;
        private final BufferedWriter writer;

        public TestSocket(final String host, final int port) {
            try {
                this.socket = connectSocket(host, port);
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void write(final String message) throws IOException {
            writer.write(message);
            writer.flush();
        }

        public String readLine() throws IOException {
            return reader.readLine();
        }

        public void close() throws IOException {
            reader.close();
            writer.close();
            socket.close();
        }

        private Socket connectSocket(final String host, final int port) throws IOException {
            final Socket socket = new Socket(host, port);
            socket.setSoTimeout(1000);
            return socket;
        }
    }
}
