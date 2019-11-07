package org.academiadecodigo.whiledlings;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.whiledlings.server.ConnectionHandler;

import static org.academiadecodigo.whiledlings.map.Color.ANSI_RESET;

public class Player {

    private String name;
    private String color;
    private String username;
    private final ConnectionHandler connection;
    private final Prompt prompt;
    private boolean playing;
    private String[][] map;

    public Player(String name, String color, ConnectionHandler connection, Prompt prompt) {
        this.name = name;
        this.color = color;
        this.username = color + name + ANSI_RESET;
        this.connection = connection;
        this.prompt = prompt;
        send("Now you're all set up " + username);
    }

    public String getName() {
        return this.name;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void send(String message) {
        connection.send(message);
    }
    public void setColor(String color){
        this.color = color;
        this.username = color + name + ANSI_RESET;
    }

    public String getUsername() {
        return username;
    }

    public String ask(StringSetInputScanner question) {
        return connection.ask(question);
    }
    public int ask(MenuInputScanner question) {
        return prompt.getUserInput(question);
    }

    public String[][] getMap() {
        return this.map;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }
}
