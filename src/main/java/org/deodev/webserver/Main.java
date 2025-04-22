package org.deodev.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        int port = 8080;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.print("Server running");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Spin up a new thread for each client
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}