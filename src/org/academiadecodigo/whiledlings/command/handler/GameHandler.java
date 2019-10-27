package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.message.Message;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public class GameHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
        connection.send(Message.FINDING_GAME);
        try {
            server.addPlayerToGame(connection.getPlayer());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
