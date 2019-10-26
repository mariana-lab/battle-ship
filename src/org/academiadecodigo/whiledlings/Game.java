package org.academiadecodigo.whiledlings;

public class Game {
    private String player1;
    private String player2;

    private int p1points;
    private int p2points;


    public boolean isFull() {
        return (player1 != null && player2 != null);
    }
}
