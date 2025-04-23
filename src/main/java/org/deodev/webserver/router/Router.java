package org.deodev.webserver.router;

import org.deodev.webserver.command.Command;
import org.deodev.webserver.command.HtmlCommand;
import org.deodev.webserver.command.JsonCommand;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String, Command> commands = new HashMap<>();

    public Router() {
        commands.put("/", new HtmlCommand());
        commands.put("/json", new JsonCommand());
    }

    public Command resolve(String path) {
        return commands.get(path);
    }
}
