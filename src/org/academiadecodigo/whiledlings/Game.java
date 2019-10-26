package org.academiadecodigo.whiledlings;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int MAX_PLAYERS = 2;
    private List<Player> players;
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
                return;
            }
        }
    }

    public void start(){
        if (hasStarted){
            return;
        }
        //send the maps to be filled
        //wait for the filled maps
        //ask player1 to play
        //check if he has got it, if yes, ask again
        //ask player 2 to guess, "


    }
}
