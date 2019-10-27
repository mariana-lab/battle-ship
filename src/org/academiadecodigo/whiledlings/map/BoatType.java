package org.academiadecodigo.whiledlings.map;

import org.academiadecodigo.whiledlings.message.Message;

import static org.academiadecodigo.whiledlings.message.Message.ASK_POSITION;


public enum BoatType {
    CARRIER(Message.CARRIER, Message.CARRIER_NUM, 5),
    BATTLESHIP(Message.BATTLESHIP, Message.BATTLESHIP_NUM, 4),
    CRUISER(Message.CRUISER, Message.CRUISER_NUM, 3),
    SUBMARINE(Message.SUBMARINE, Message.SUBMARINE_NUM, 3),
    DESTROYER(Message.DESTROYER, Message.DESTROYER_NUM, 2);

    private String name;
    private String cellsNums;
    private int size;

    BoatType(String name, String cellsNums, int size) {
        this.name = name;
        this.cellsNums = cellsNums;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getCellsNums() {
        return cellsNums;
    }

    public static MapInfoList getInitialBoatsInfo(BoatType type, String header) {

        MapInfoList mapInfoList = new MapInfoList(MapHandler.CELL_NUMBER + 1, header);

        for (int row = 0; row < mapInfoList.size()-1; row++) {
            //every other line
            if (row % 2 == 0) {
                mapInfoList.add(BoatType.values()[row / 2].getCellsNums() + BoatType.values()[row / 2].getName());
                mapInfoList.add("");
            }
        }
        return mapInfoList;
    }
}
