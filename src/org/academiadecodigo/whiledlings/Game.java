package org.academiadecodigo.whiledlings;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.whiledlings.map.BoatType;
import org.academiadecodigo.whiledlings.map.Direction;
import org.academiadecodigo.whiledlings.map.MapHandler;
import org.academiadecodigo.whiledlings.message.Message;

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


    private class MapSetup implements Runnable {
        private final Player player;
        private final String[][] map;
        private String letter;
        private String number;
        private Direction direction;

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

                askPosition(boatType);

                MapHandler.paintCells(map, letter, number, direction, boatType, MapHandler.MoveType.MARK.getSymbol());
                player.send(MapHandler.buildInitial(map));

            }
        }

        private void askPosition(BoatType boatType) {
            StringSetInputScanner positionQuestion = new StringSetInputScanner(MapHandler.positions);
            positionQuestion.setMessage(Message.ASK_POSITION + boatType.getName());
            positionQuestion.setError(Message.INVALID_CELL_ERROR);
            player.send(MapHandler.buildInitial(map));

            String position = player.ask(positionQuestion);
            this.letter = position.split("")[0];
            this.number = position.split("")[1];


            MenuInputScanner directionMenu = new MenuInputScanner(new String[]{"Horizontal", "Vertical"});
            directionMenu.setMessage(Message.ASK_DIRECTION + boatType.getName());
            directionMenu.setError(Message.INVALID_MENU_ERROR);

            player.send(MapHandler.buildInitial(map));
            this.direction = Direction.values()[player.ask(directionMenu) - 1];

            if (MapHandler.canMark(map, letter, number, direction, boatType)) {
                askPosition(boatType);
            }
        }
    }
}
