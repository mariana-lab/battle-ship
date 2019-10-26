package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.message.Message;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public class HelpHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
            connection.send(Message.HELP);
    }
}
