package org.deodev.webserver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {

    @Test
    void testClientHandlerResponds() throws Exception {
        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();


        new Thread(() -> {
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try (Socket client = new Socket("localhost", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            out.println("GET / HTTP/1.1");
            out.println();

            String responseLine = in.readLine();
            assertTrue(responseLine.contains("HTTP/1.1"));
        }

        serverSocket.close();
    }

}