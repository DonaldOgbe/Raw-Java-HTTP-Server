package org.deodev.webserver;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseBuilderTest {

    @Test
    void testBuildResponse() {
        String body = "<h1>Hello</h1>";
        String contentType = "text/html";

        String response = ResponseBuilder.build(contentType, body);

        assertTrue(response.startsWith("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Content-Type: text/html"));
        assertTrue(response.contains("Content-Length: " + body.length()));
        assertTrue(response.endsWith(body));
    }
}