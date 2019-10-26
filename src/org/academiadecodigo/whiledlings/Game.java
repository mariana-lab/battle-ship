package org.academiadecodigo.whiledlings;
import org.academiadecodigo.whiledlings.map.MapHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int MAX_PLAYERS = 2;
    private List<Player> players;
    private List<String[][]> maps;
    private boolean hasStarted;

    public Game() {
        players = Collections.synchronizedList(new LinkedList<Player>());
        hasStarted = false;
    }

    public Game(Player player) {
        this();
        addPlayer(player);
    }

    public boolean isFull() {
        return players.size()>= MAX_PLAYERS;
    }

    public void addPlayer(Player player) {
        synchronized (players) {
            if(!isFull()){
                players.add(player);
                maps.add(MapHandler.getNewMap());
                System.out.println(player.getUsername() + "has been added to game");
                return;
            }
        }
    }

    public void start(){
        System.out.println("game has started");
        if (hasStarted){
            return;
        }

        //send the maps to be filled
        Thread mapSetup1 = new Thread(new MapSetup(players.get(0),maps.get(0)));
        MapSetup mapSetup2 = new MapSetup(players.get(1), maps.get(1));
        mapSetup1.start();
        mapSetup2.run();
        //wait for the filled maps
        //ask player1 to play
        //check if he has got it, if yes, ask again
        //ask player 2 to guess, "
    }


    private class MapSetup implements Runnable {
        private final Player player;
        private final String[][] map;

        public MapSetup(Player player, String[][] map) {
            this.player = player;
            this.map = map;
        }

        @Override
        public void run() {
            //build initial map
            player.send(MapHandler.build(map));
            //ask for boat 1 initpos
            //ask for boat 1 final pos, keep doing this for every boat
        }
    }
}
