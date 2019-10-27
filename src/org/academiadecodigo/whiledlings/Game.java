package org.academiadecodigo.whiledlings;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
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

    public Game() {
        players = Collections.synchronizedList(new LinkedList<Player>());
        maps = Collections.synchronizedList(new LinkedList<String[][]>());
        hasStarted = false;
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
                System.out.println(player.getUsername() + " has been added to game");
                System.out.println(players.size());
                return;
            }
        }
    }

    public void start() {
        if (hasStarted) {
            return;
        }

        //send the maps to be filled
        Thread mapSetup1 = new Thread(new MapSetup(players.get(0), maps.get(0)));
        MapSetup mapSetup2 = new MapSetup(players.get(1), maps.get(1));
        mapSetup1.start();
        mapSetup2.run();
        //wait for the filled maps
        //ask player1 to play
        //check if he has got it, if yes, ask again
        //ask player 2 to guess, "
    }

    public Player getOponent(Player player){
        for (Player p : players) {
            if(!p.equals(player)){
                return p;
            }
        }
        return player;
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
            markBoats();
        }

        private void markBoats() {

            for (BoatType boatType : BoatType.values()) {

                Boat boat = askBoatPosition(boatType);
                MapHandler.paintBoat(map, boat);

                System.out.println(MapHandler.buildInitial(map, BoatType.getInitialBoatsInfo(boatType, ASK_POSITION + boatType.getName())));
                player.send(MapHandler.buildInitial(map, BoatType.getInitialBoatsInfo(boatType, ASK_POSITION + boatType.getName())));
            }
            player.send("You've completed your strategy, now it's time we wait for " + getOponent(player).getUsername());
        }

        private Boat askBoatPosition(BoatType boatType) {

            StringSetInputScanner positionQuestion = QuestionHandler.buildQuestion(ASK_POSITION + boatType.getName() + "\n", INVALID_CELL_ERROR, MapHandler.positions);
            MenuInputScanner directionQuestion = QuestionHandler.buildQuestion(ASK_DIRECTION + boatType.getName(), INVALID_MENU_ERROR, Direction.getAll());

            //initial position of boat
            player.send(MapHandler.buildInitial(map, boatType));
            String position = player.ask(positionQuestion);

            //direction of boat
            int directionInt = player.ask(directionQuestion);
            Direction direction = Direction.values()[directionInt - 1];

            //verification of limits and other boats
            if (!MapHandler.canMark(map, position, direction, boatType)) {
                player.send(MARKED_POSITION_ERROR);
                return askBoatPosition(boatType);
            }

            return new Boat(position, direction, boatType, MapHandler.MoveType.MARK.getSymbol());
        }
    }
}
