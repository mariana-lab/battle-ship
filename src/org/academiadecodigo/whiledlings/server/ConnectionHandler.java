package org.academiadecodigo.whiledlings.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.command.Command;
import org.academiadecodigo.whiledlings.map.Color;
import org.academiadecodigo.whiledlings.server.Server;

import java.io.*;
import java.net.Socket;

import static org.academiadecodigo.whiledlings.message.Message.*;

public class ConnectionHandler implements Runnable {

    private final Server server;
    private final Socket socket;

    private final Prompt prompt;
    private BufferedReader input;
    private PrintWriter output;
    private Player player;

    public ConnectionHandler(Server server, Socket socket) throws IOException {

        this.server = server;
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));

    }

    @Override
    public void run() {


        player = setUpPlayer();
        server.addPlayer(player);

        while (true) {
            try {
                String str = input.readLine();
                Command.getCommand(str).getCommandHandler().handle(server, this, str);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private Player setUpPlayer() {
        return new Player(askName(), askColor(), this, prompt);
    }

    private String askColor() {
        //gets String[] with names of colors
        String[] colors = Color.userOprions.keySet().toArray(new String[Color.userOprions.size()]);
        MenuInputScanner colorQuest = new MenuInputScanner(colors);
        int i = prompt.getUserInput(colorQuest);
        //returns the code of the color
        return Color.userOprions.get(colors[i - 1]);
    }

    private String askName() {

        StringInputScanner nameQuest = new StringInputScanner();
        nameQuest.setMessage(ASK_NAME);
        nameQuest.setError(NAME_EMPTY_ERROR);
        String name = prompt.getUserInput(nameQuest);

        if (server.hasPlayer(name)) {
            send(NAME_EXISTS_ERROR);
            return askName();
        }

        return name;
    }


    public void send(String s) {
        output.println(s);
        output.flush();
    }

    public String getUsername() {
        return player.getUsername();
    }
}
