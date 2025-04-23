package org.deodev.webserver.router;

import org.deodev.webserver.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    @Test
    void testResolveReturnsCorrectCommand() {
        Router router = new Router();

        Command htmlCommand = router.resolve("/");
        Command jsonCommand = router.resolve("/json");
        Command unknownCommand = router.resolve("/unknown");

        assertNotNull(htmlCommand, "Should return a command for '/'");
        assertNotNull(jsonCommand, "Should return a command for '/json'");
        assertNull(unknownCommand, "Should return null for unknown path");
    }
}