package org.academiadecodigo.whiledlings.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.map.Color;

import java.io.*;
import java.net.Socket;

import static org.academiadecodigo.whiledlings.message.Message.*;

public class ConnectionHandler implements Runnable {

    private final BattleShipServer server;
    private final Socket socket;

    private final Prompt prompt;
    private BufferedReader input;
    private PrintWriter output;
    private String name;
    private String color;
    private String username;
    private Player player;

    public ConnectionHandler(BattleShipServer battleShipServer, Socket socket) throws IOException {

        this.server = battleShipServer;
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));

    }

    @Override
    public void run() {


        player = setUpPlayer();
        server.addPlayer(player);

        while (true){
            try {
                String str = input.readLine();
                if(!str.startsWith("/")){
                    server.broadcast(player.getUsername() + ": " + str);
                    continue;
                }
                if(!Command.exists(str)){
                    send(COMMAND_NOT_FOUND_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO: 24/10/2019  welcome to the general room
        // TODO: 24/10/2019  new thread to listen
        // TODO: 24/10/2019  anything writen is broadcasted to waiting clients


    }

    private Player setUpPlayer() {
        return new Player( askName() , askColor() ,this, prompt);
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

        if (server.hasClient(name)) {
            send(NAME_EXISTS_ERROR);
            return askName();
        }

        return name;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void send(String s){
        output.println(s);
        output.flush();
    }
}
