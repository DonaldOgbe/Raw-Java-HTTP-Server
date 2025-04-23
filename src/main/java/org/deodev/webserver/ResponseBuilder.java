package org.deodev.webserver;

public class ResponseBuilder {
    public static String build(String contentType, String body) {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " +contentType+ "\r\n" +
                "Content-Length: " +body.length()+ "\r\n" +
                "\r\n" +
                body;
    }
}
