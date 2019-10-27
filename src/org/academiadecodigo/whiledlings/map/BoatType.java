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

    public static MapInfoList getInitialBoatsInfo(BoatType type){
        MapInfoList mapInfoList = new MapInfoList(MapHandler.CELL_NUMBER + 1, ASK_POSITION + type.name);
        for (int row = 1; row <= mapInfoList.size(); row++) {

            //every other line
            if (row % 2 != 0) { // row <list.size
                //linkedList.get(10);
                mapInfoList.add(BoatType.values()[row / 2].getCellsNums());
                mapInfoList.add(BoatType.values()[row / 2].getName());
            }
        }
        return mapInfoList;
    }
}
