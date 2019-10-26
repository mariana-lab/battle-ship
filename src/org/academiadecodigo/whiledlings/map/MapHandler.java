package org.academiadecodigo.whiledlings.map;

import static org.academiadecodigo.whiledlings.map.Color.*;
import static org.academiadecodigo.whiledlings.message.Message.*;

public class MapHandler {

    public static final int CELL_NUMBER = 10;

    public static final String LEFT_WALL = "[";
    public static final String RIGHT_WALL = "]";
    public static final String EMPTY_CELL = " ";

    public static final String SPACE_BETWEEN_CELLS = " ";
    public static final String SPACE_BETWEEN_MAPS = "   ";
    public static final String PADDING = SPACE_BETWEEN_MAPS + SPACE_BETWEEN_CELLS;


    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
    public static String[] boatStrings = {CARRIER, CARRIER_NUM, BATTLESHIP, BATTLESHIP_NUM, CRUISER, CRUISER_NUM, SUBMARINE, SUBMARINE_NUM, DESTROYER, DESTROYER_NUM};


    public static void main(String[] args) {

        String[][] map = getNewMap();
        paintCell(map, "B", "4", PlayType.MARK.symbol);
        paintCell(map, "C", "4", PlayType.MISS.symbol);
        System.out.println(build(map, map));
        System.out.println(buildInitial(map));
        System.out.println(buildInitial(map));
    }

    private static String buildInitial(String[][] map) {
        StringBuilder initialMapBuilder = new StringBuilder();
        initialMapBuilder.append(PADDING);

        //LETTERS
        initialMapBuilder.append(buildLetters(map[0].length));
        initialMapBuilder.append(ASK_IN_POSITION_OF_SHIP);
        initialMapBuilder.append("\n");


        for (int row = 0; row < map.length; row++) {
            initialMapBuilder.append(buildRow(map, row));
            initialMapBuilder.append(SPACE_BETWEEN_MAPS);
            //every other line
            if (row % 2 != 0)
                initialMapBuilder.append(boatStrings[row] + boatStrings[row - 1]);
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
            mapsBuilder.append(buildRow(map1, row));
            mapsBuilder.append(SPACE_BETWEEN_MAPS);
            mapsBuilder.append(buildRow(map2, row));
            mapsBuilder.append("\n");

        }

        return mapsBuilder.toString();
    }

    private static String buildRow(String[][] map, int row) {
        StringBuilder rowBuilder = new StringBuilder();
        //NUMBER checking if single digit or double digit
        rowBuilder.append((row + 1) + ((row == 9) ? "" : SPACE_BETWEEN_CELLS));

        //CELLS of row
        for (int col = 0; col < map[row].length; col++) {
            rowBuilder.append(ANSI_BLUE_BACKGROUND);
            rowBuilder.append(SPACE_BETWEEN_CELLS);

            String color = (map[col][row].equals(" ")) ? ANSI_BLUE_BACKGROUND : ANSI_RED_BACKGROUND;
            rowBuilder.append(color);
            rowBuilder.append(LEFT_WALL + map[col][row] + color + RIGHT_WALL);
        }

        rowBuilder.append(ANSI_RESET);
        return rowBuilder.toString();
    }

    private static String buildLetters(int cols) {
        StringBuilder lettersBuilder = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            lettersBuilder.append(EMPTY_CELL + letters[i] + EMPTY_CELL + SPACE_BETWEEN_CELLS);
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
            mapBuilder.append(buildRow(map, row));
            mapBuilder.append(SPACE_BETWEEN_MAPS);
            mapBuilder.append("\n");
        }

        return mapBuilder.toString();
    }

    public static void paintCell(String[][] map, String letter, String row, String symbol) {
        map[getColFromLetter(letter)][Integer.parseInt(row)] = symbol;
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


    public enum PlayType {
        MARK("X"),
        HIT("H"),
        MISS("M");

        private final String symbol;

        PlayType(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

}
