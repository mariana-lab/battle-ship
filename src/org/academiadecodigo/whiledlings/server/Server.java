package org.academiadecodigo.whiledlings.server;

import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private final ExecutorService executorService;
    private List<Game> games;
    private ConcurrentHashMap<String, Player> players;


    public Server() {
        this.games = Collections.synchronizedList(new LinkedList<Game>());
        this.players = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
        try {
            this.serverSocket = new ServerSocket(4040);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();

        try {
            while (true) {
                Socket socket = server.serverSocket.accept();
                server.executorService.submit(new ConnectionHandler(server, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasPlayer(String name) {
        synchronized (players) {
            for (String s : players.keySet()) {
                System.out.println(s);
            }
        }
        return players.containsKey(name);
    }

    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getName());
    }

    public void broadcast(String message) {
        synchronized (players) {
            for (String name : players.keySet()) {
                if (!players.get(name).isPlaying()) {
                    players.get(name).send(message);
                }
            }
        }
    }

    public String listPlayers() {
        String list = "";
        for (String name : players.keySet()) {
            list += "/" + players.get(name).getUsername() + " ";
        }
        return list;
    }

    public void addPlayerToGame(Player player) {
        for (Game game: games) {
            if(game.isFull()){
                continue;
            }
            game.addPlayer(player);
        }
        // TODO: 26/10/2019 give random awsome names to the rooms
        games.add(new Game(player));
    }



    //atribui uma sala, hash map room
    //guarda a pontuação máxima num ficheiro, baseado em tempo de decisão/precisão

}
