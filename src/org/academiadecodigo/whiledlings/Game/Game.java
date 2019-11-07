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
    private final List<Player> players;
    private List<String[][]> maps;
    private boolean hasStarted;
    private boolean isWaiting;
    private GameStage gameStage;

    public Game() {
        players = Collections.synchronizedList(new LinkedList<Player>());
        maps = Collections.synchronizedList(new LinkedList<String[][]>());
        hasStarted = false;
        isWaiting = false;
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
                String[][] map = MapHandler.getNewMap();
                maps.add(map);
                player.setMap(map);
                player.send(GAME_WELCOME + player.getUsername());
                player.send(GAME_INFO);
                getOponent(player).send(player.getUsername() + OPONENT_ARRIVAL);
            }
        }
   }

    public void start() {
        if (hasStarted) {
            return;
        }

        //depending the state of the game different handlers will be called
        for (GameStage stage : GameStage.values()) {
            gameStage = stage;

            Thread gameManagerPlayer1 = new Thread(new GameManager(players.get(0), maps.get(0)));
            GameManager gameManagerPlayer2 = new GameManager(players.get(1), maps.get(1));

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

    public boolean hasCompletedMap() {
        // TODO: 28/10/2019 abstract map to class board, and it can return a map :)
        for (String[][] map : maps) {
            if (isCompleted(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCompleted(String[][] map) {
        int hitCounter = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                hitCounter += map[i][j].equals(MapHandler.MoveType.HIT) ? 1 : 0;
                System.out.println(hitCounter);
            }
        }

        return hitCounter >= BoatType.getMaxHits();
    }

    public void waitOrNotifyOtherPlayer(Player player) {
        synchronized (gameStage){
            if(isWaiting){
                send("You both are done, here are your choices: \n");
                gameStage.notify();
                isWaiting = false;
                return;
            }
            try {
                player.send("Please wait for " + getOponent(player).getUsername() + "'s choice...");
                isWaiting = true;
                gameStage.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            System.out.println(gameStage.name());
            gameStage.getStageHandler().handle(Game.this, player, map);
            System.out.println(gameStage.name() + " done");
        }

    }
}
