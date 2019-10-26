package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.message.Message;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public class ListHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
        // TODO: 25/10/2019 ask server to list and send it to player
        connection.send(Message.PM_HELP);
        connection.send(server.listPlayers());
        // TODO: 25/10/2019 think about crazy stats like the players that ask for help the most
    }
}
