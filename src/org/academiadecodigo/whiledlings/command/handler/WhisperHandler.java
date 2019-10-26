package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public class WhisperHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
        if (server.hasPlayer(message.split("/")[1])) {
            //verificou se tinha e agora temos de mandar para ele server.getPlayer.send(connection.getusername+:)
        }
    }
}
