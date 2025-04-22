package org.deodev.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }


    @Override
    public void run() {

        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true)) {
            String requestLine = in.readLine();

            if (requestLine != null) {
                String path = requestLine.split(" ")[1];
                String method = requestLine.split(" ")[0];

                if (method.equals("GET")) {
                    handleRequest(path, out);
                } else {
                    String errorResponse = buildErrorResponse("405 Method Not Allowed");
                    out.write(errorResponse);
                }

                String body = switch (path) {
                    case "/" -> readFile("src/main/resources/index.html");
                    case "/json" -> readFile("src/main/resources/data.json");
                    default -> "404 Not Found";
                };

                String response = buildResponse(path, body);
                out.write(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return "404 Not Found";
        }
    }

    public void handleRequest(String path, PrintWriter out) {
        String body;
        String contentType;

        switch (path) {
            case "/":
                body = readFile("src/main/resources/index.html");
                contentType = "text/html";
                break;
            case "/json":
                body = readFile("src/main/resources/data.json");
                contentType = "application/json";
                break;
            default:
                body = body = "404 Not Found";
                contentType = "text/plain";
        }

        String response = buildResponse(contentType, body);
        out.write(response);
    }

    public String buildResponse(String contentType, String body) {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " +contentType+ "\r\n" +
                "Content-Length: " +body.length()+ "\r\n" +
                "\r\n" +
                body;
    }

    public String buildErrorResponse(String statusCode) {
        return "HTTP/1.1 " + statusCode + "\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: " + statusCode.length() + "\r\n" +
                "\r\n" +
                statusCode;
    }
}
