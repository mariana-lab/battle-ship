package org.academiadecodigo.whiledlings.map;

import java.util.HashSet;
import java.util.Set;

import static org.academiadecodigo.whiledlings.map.Color.*;
import static org.academiadecodigo.whiledlings.message.Message.*;

public class MapHandler {

    public static final int CELL_NUMBER = 10;

    public static final String LEFT_WALL = "[";
    public static final String RIGHT_WALL = "]";
    public static final String EMPTY_CELL = " ";

    public static final String SPACE_BETWEEN_CELLS = " ";
    public static final String SPACE_BETWEEN_MAPS = "   ";
    public static final String PADDING = SPACE_BETWEEN_MAPS;


    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
    public static Set<String> positions = generatePositions();

    private static Set<String> generatePositions() {
        Set<String> positions = new HashSet<>();
        for (String letter : letters) {
            for (int i = 1; i <= CELL_NUMBER; i++) {
                positions.add(letter + i);
                positions.add(letter.toLowerCase() + i);
            }
        }
        return positions;
    }

    public static void main(String[] args) {

        String[][] map = getNewMap();
        //paintCell(map, 3, 3, MoveType.MARK.symbol);
        //paintCell(map, 4, 4, MoveType.MISS.symbol);
        System.out.println(buildInitial(map, BoatType.getInitialBoatsInfo(BoatType.CRUISER, ASK_POSITION)));
        System.out.println(buildInitial(map, BoatType.getInitialBoatsInfo(BoatType.CRUISER, ASK_POSITION)));
        System.out.println(canMark(map, "C3", Direction.HORIZONTAL, BoatType.CRUISER));
        paintBoat(map, new Boat("C3", Direction.HORIZONTAL, BoatType.CRUISER, MoveType.MARK.getSymbol()));
        System.out.println(build(map));
        System.out.println(build(map, map));
    }

    public static String buildInitial(String[][] map, MapInfoList infoList) {

        StringBuilder initialMapBuilder = new StringBuilder();

        initialMapBuilder.append(PADDING);
        //LETTERS
        initialMapBuilder.append(buildLetters(map[0].length));
        initialMapBuilder.append(infoList.getHeader());
        initialMapBuilder.append("\n");


        for (int row = 0; row < map.length; row++) {
            initialMapBuilder.append(buildRow(map, row, false));
            initialMapBuilder.append(SPACE_BETWEEN_MAPS);
            initialMapBuilder.append(infoList.toArray()[row+1]);
            initialMapBuilder.append("\n");

        }
        return initialMapBuilder.toString();
    }

    public static String buildInitial(String[][] map, BoatType boatType) {
        StringBuilder initialMapBuilder = new StringBuilder();

        initialMapBuilder.append(PADDING);

        //LETTERS
        initialMapBuilder.append(buildLetters(map[0].length));
        initialMapBuilder.append(ASK_POSITION + boatType.getName());
        initialMapBuilder.append("\n");


        for (int row = 0; row < map.length; row++) {
            initialMapBuilder.append(buildRow(map, row, false));
            initialMapBuilder.append(SPACE_BETWEEN_MAPS);
            //every other line
            if (row % 2 != 0) { // row <list.size
                //linkedList.get(10);
                initialMapBuilder.append(BoatType.values()[row / 2].getCellsNums());
                initialMapBuilder.append(BoatType.values()[row / 2].getName());
            }
            initialMapBuilder.append("\n");

        }
        return initialMapBuilder.toString();
    }


    public static String[][] getNewMap() {
        String[][] map = new String[CELL_NUMBER][CELL_NUMBER];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[j][i] = EMPTY_CELL;
            }
        }

        return map;
    }

    public static String build(String[][] map1, String[][] map2) {
        StringBuilder mapsBuilder = new StringBuilder();
        mapsBuilder.append(PADDING);

        //LETTERS
        mapsBuilder.append(buildLetters(map1[0].length));
        mapsBuilder.append(SPACE_BETWEEN_CELLS + SPACE_BETWEEN_CELLS);
        mapsBuilder.append(buildLetters(map2[0].length));
        mapsBuilder.append("\n");


        for (int row = 0; row < map1.length; row++) {
            mapsBuilder.append(buildRow(map1, row, true));
            mapsBuilder.append(SPACE_BETWEEN_MAPS);
            mapsBuilder.append(buildRow(map2, row, false));
            mapsBuilder.append("\n");

        }

        return mapsBuilder.toString();
    }

    private static String buildRow(String[][] map, int row, boolean isEnemyMap) {
        StringBuilder rowBuilder = new StringBuilder();
        //NUMBER checking if single digit or double digit
        rowBuilder.append((row + 1) + ((row == 9) ? "" : SPACE_BETWEEN_CELLS));

        //CELLS of row
        for (int col = 0; col < map[row].length; col++) {
            //rowBuilder.append(ANSI_BLUE_BACKGROUND);
            rowBuilder.append(SPACE_BETWEEN_CELLS);

            String color = MoveType.getColor(map[col][row], isEnemyMap);
            rowBuilder.append(color);
            rowBuilder.append(LEFT_WALL + (map[col][row].equals(MoveType.MARK.symbol) ? " " : map[col][row]) + color + RIGHT_WALL);
        }

        rowBuilder.append(ANSI_RESET);
        return rowBuilder.toString();
    }

    private static String buildLetters(int cols) {
        StringBuilder lettersBuilder = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            lettersBuilder.append(ANSI_RESET + EMPTY_CELL + letters[i] + EMPTY_CELL + SPACE_BETWEEN_CELLS);
        }
        lettersBuilder.append(SPACE_BETWEEN_MAPS);
        return lettersBuilder.toString();
    }

    public static String build(String[][] map) {
        StringBuilder mapBuilder = new StringBuilder();
        mapBuilder.append("\n" + PADDING);
        //LETTERS
        mapBuilder.append(buildLetters(map[0].length));
        mapBuilder.append("\n");

        for (int row = 0; row < map.length; row++) {
            mapBuilder.append(buildRow(map, row, false));
            mapBuilder.append(SPACE_BETWEEN_MAPS);
            mapBuilder.append("\n");
        }

        return mapBuilder.toString();
    }


    public static int getColFromLetter(String l) {
        int col = -1;
        for (int i = 0; i < letters.length; i++) {
            if (l.equals(letters[i]) || l.equals(letters[i].toLowerCase())) {
                col = i;
                return col;
            }
        }
        return col;
    }

    public static int getRowFromNumber(String n) {
        return Integer.parseInt(n) - 1;
    }

    public static boolean canMark(String[][] map, String position, Direction direction, BoatType boatType) {
        int col = getColFromLetter(position.split("")[0]);
        int row = getRowFromNumber(position.substring(1));
        int size = boatType.getSize();

        //out of limits
        if ((direction.equals(Direction.VERTICAL) ? row : col) + size - 1 >= CELL_NUMBER) {
            System.out.println("Letter, col: " + col);
            System.out.println("number, row: " + row);
            System.out.println(col + size);
            return false;
        }

        //was marked
        for (int i = 0; i < size - 1; i++) {

            if (!map[col][row].equals(EMPTY_CELL)) {

                return false;
            }
            row += direction.equals(Direction.VERTICAL) ? 1 : 0;
            col += direction.equals(Direction.HORIZONTAL) ? 1 : 0;
        }
        return true;
    }

    public static void paintCell(String[][] map, int col, int row, String symbol) {
        map[col][row] = symbol;
    }

    public static void paintBoat(String[][] map, Boat boat) {

        int col = getColFromLetter(boat.getPosition().split("")[0]);
        int row = getRowFromNumber(boat.getPosition().substring(1));
        int size = boat.getBoatType().getSize();


        //was marked
        for (int i = 0; i < size; i++) {
            paintCell(map, col, row, boat.getSymbol());
            row += boat.getDirection().equals(Direction.VERTICAL) ? 1 : 0;
            col += boat.getDirection().equals(Direction.HORIZONTAL) ? 1 : 0;
        }
    }

    public enum MoveType {
        EMPTY(" ", ANSI_BLUE_BACKGROUND),
        MARK("X", ANSI_WHITE_BACKGROUND),
        HIT("H", ANSI_RED_BACKGROUND),
        MISS("M", ANSI_CYAN_BACKGROUND);

        private final String symbol;
        private final String color;

        MoveType(String symbol, String color) {
            this.symbol = symbol;
            this.color = color;
        }

        public static String getColor(String symbol, boolean isEnemyMap) {
            if (isEnemyMap && symbol.equals(MARK.symbol)) {
                return EMPTY.color;
            }

            for (MoveType type : values()) {
                if (type.symbol.equals(symbol)) {
                    return type.color;
                }
            }
            return EMPTY.color;
        }


        public String getSymbol() {
            return symbol;
        }

        public String getColor() {
            return color;
        }
    }

}
