package org.deodev.webserver.command;

import org.deodev.webserver.ResponseBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlCommand implements Command{
    @Override
    public String execute() {
        String body;
        try {
            body = Files.readString(Paths.get("src/main/resources/index.html"));
        } catch (IOException e) {
            e.printStackTrace();
            body = "404 Not Found";
        }
        return ResponseBuilder.build("text/html", body);
    }
}
