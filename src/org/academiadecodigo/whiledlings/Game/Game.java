package org.academiadecodigo.whiledlings.Game;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.map.Boat;
import org.academiadecodigo.whiledlings.map.BoatType;
import org.academiadecodigo.whiledlings.map.Direction;
import org.academiadecodigo.whiledlings.map.MapHandler;

import static org.academiadecodigo.whiledlings.message.Message.*;

import org.academiadecodigo.whiledlings.message.QuestionHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int MAX_PLAYERS = 2;
    private List<Player> players;
    private List<String[][]> maps;
    private boolean hasStarted;
    private GameStage gameStage;

    public Game() {
        players = Collections.synchronizedList(new LinkedList<Player>());
        maps = Collections.synchronizedList(new LinkedList<String[][]>());
        hasStarted = false;
        gameStage = GameStage.SETUP;
    }

    public Game(Player player) {
        this();
        addPlayer(player);
    }

    public boolean isFull() {
        return players.size() >= MAX_PLAYERS;
    }

    public void addPlayer(Player player) {
        synchronized (players) {
            if (!isFull()) {
                players.add(player);
                maps.add(MapHandler.getNewMap());
                player.send(GAME_WELCOME + player.getUsername());
                player.send(GAME_INFO);
                getOponent(player).send(player.getUsername() + OPONENT_ARRIVAL);
                return;
            }
        }
    }

    public void start() {
        if (hasStarted) {
            return;
        }

        Thread gameManagerPlayer1 = new Thread(new GameManager(players.get(0), maps.get(0)));
        GameManager gameManagerPlayer2 = new GameManager(players.get(1), maps.get(1));

        //depending the state of the game different handlers will be called
        for (GameStage stage : GameStage.values()) {
            gameStage = stage;

            send(gameStage.getInitialMessage());

            gameManagerPlayer1.start();
            gameManagerPlayer2.run();

            send(gameStage.getFinalMessage());
        }

        //wait for the filled maps
        //ask player1 to play
        //check if he has got it, if yes, ask again
        //ask player 2 to guess, "
    }

    public Player getOponent(Player player) {
        for (Player p : players) {
            if (!p.equals(player)) {
                return p;
            }
        }
        return player;
    }

    private void send(String message) {
        for (Player player : players) {
            player.send(message);
        }
    }



    private class GameManager implements Runnable {
        private final Player player;
        private final String[][] map;


        public GameManager(Player player, String[][] map) {
            this.player = player;
            this.map = map;
        }

        @Override
        public void run() {
            gameStage.getStageHandler().handle(Game.this, player, map);
        }

    }
}
