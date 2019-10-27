package org.academiadecodigo.whiledlings.map;

import org.academiadecodigo.whiledlings.message.Message;

public enum BoatType {
    CARRIER(Message.CARRIER, 5),
    BATTLESHIP(Message.BATTLESHIP, 4),
    CRUISER(Message.CRUISER,3),
    SUBMARINE(Message.SUBMARINE,3),
    DESTROYER(Message.DESTROYER,2);

    private String name;
    private int size;

    BoatType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
