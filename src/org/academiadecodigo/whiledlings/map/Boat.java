package org.academiadecodigo.whiledlings.map;

public class Boat {

    private final String position;
    private final Direction direction;
    private final BoatType boatType;
    private final String symbol;

    public Boat(String position, Direction direction, BoatType boatType, String symbol) {
        this.position = position;
        this.direction = direction;
        this.boatType = boatType;
        this.symbol = symbol;
    }

    public String getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public String getSymbol() {
        return symbol;
    }
}
