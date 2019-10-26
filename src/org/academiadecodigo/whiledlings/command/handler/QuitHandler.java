package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public class QuitHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
        // TODO: 26/10/2019 return both players to general room
        // TODO: 26/10/2019 show game stats and save them

    }
}
