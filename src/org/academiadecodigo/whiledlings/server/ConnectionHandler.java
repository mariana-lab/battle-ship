package org.academiadecodigo.whiledlings.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.command.Command;
import org.academiadecodigo.whiledlings.map.Color;
import static org.academiadecodigo.whiledlings.message.Message.*;
import org.academiadecodigo.whiledlings.message.QuestionHandler;

import java.io.*;
import java.net.Socket;


public class ConnectionHandler implements Runnable {

    private final Server server;

    private Prompt prompt;
    private BufferedReader input;
    private PrintWriter output;
    private Player player;

    public ConnectionHandler(Server server, Socket socket) throws IOException {

        this.server = server;
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
        String[] colors = Color.colorOptions.keySet().toArray(new String[Color.colorOptions.size()]);
        MenuInputScanner colorQuest = new MenuInputScanner(colors);
        int i = prompt.getUserInput(colorQuest);
        //returns the code of the color
        return Color.colorOptions.get(colors[i - 1]);
    }

    private String askName() {

        String name = ask(QuestionHandler.buildQuestion(ASK_NAME, NAME_EMPTY_ERROR));

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

    public Player getPlayer() {
        return this.player;
    }

    public synchronized String ask(StringSetInputScanner question) {
        return prompt.getUserInput(question);
    }

    public synchronized String ask(StringInputScanner question) {
        return prompt.getUserInput(question);
    }
}
