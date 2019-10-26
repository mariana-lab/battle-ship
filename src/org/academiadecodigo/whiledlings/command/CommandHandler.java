package org.academiadecodigo.whiledlings.command;

import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public interface CommandHandler {
    void handle(Server server, ConnectionHandler connection, String message);
}
