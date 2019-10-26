package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

import static org.academiadecodigo.whiledlings.message.Message.COMMAND_NOT_FOUND_ERROR;

public class InvalidHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
        connection.send(COMMAND_NOT_FOUND_ERROR);
    }
}
