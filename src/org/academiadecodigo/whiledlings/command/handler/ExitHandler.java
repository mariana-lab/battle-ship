package org.academiadecodigo.whiledlings.command.handler;

import org.academiadecodigo.whiledlings.command.CommandHandler;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;
import org.academiadecodigo.whiledlings.server.Server;

public class ExitHandler implements CommandHandler {
    @Override
    public void handle(Server server, ConnectionHandler connection, String message) {
        server.broadcast("Sad to say but " + connection.getUsername() + " has left.");
        //TODO: cannot exit without closing the game
        //TODO: 25/10/2019 close sockets of connection, clear players list and empty current game
    }
}
