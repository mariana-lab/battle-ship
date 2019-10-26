package org.academiadecodigo.whiledlings.map;

import org.academiadecodigo.whiledlings.message.Message;

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
        print(map, map);
        print(map);
        printInitial(map);
    }

    private static void printInitial(String[][] map) {
        System.out.print(PADDING);

        //LETTERS
        printLetters(map[0].length);
        System.out.print(ASK_IN_POSITION_OF_SHIP);
        System.out.print("\n");


        for (int row = 0; row < map.length; row++) {
            printRow(map, row);
            System.out.print(SPACE_BETWEEN_MAPS);
            //every other line
            if (row % 2 != 0)
                System.out.print(boatStrings[row] + boatStrings[row - 1]);
            System.out.print("\n");

        }
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

    public static void print(String[][] map1, String[][] map2) {

        System.out.print(PADDING);

        //LETTERS
        printLetters(map1[0].length);
        System.out.print(SPACE_BETWEEN_CELLS + SPACE_BETWEEN_CELLS);
        printLetters(map2[0].length);
        System.out.print("\n");


        for (int row = 0; row < map1.length; row++) {
            printRow(map1, row);
            System.out.print(SPACE_BETWEEN_MAPS);
            printRow(map2, row);
            System.out.print("\n");

        }


    }

    private static void printRow(String[][] map, int row) {
        //NUMBER checking if single digit or double digit
        System.out.print((row + 1) + ((row == 9) ? "" : SPACE_BETWEEN_CELLS));

        //CELLS of row
        for (int col = 0; col < map[row].length; col++) {
            System.out.print(ANSI_BLUE_BACKGROUND);
            System.out.print(SPACE_BETWEEN_CELLS);

            String color = (map[col][row].equals(" ")) ? ANSI_BLUE_BACKGROUND : ANSI_RED_BACKGROUND;
            System.out.print(color);
            System.out.print(LEFT_WALL + map[col][row] + color + RIGHT_WALL);
        }

        System.out.print(ANSI_RESET);
    }

    private static void printLetters(int cols) {

        for (int i = 0; i < cols; i++) {
            System.out.print(EMPTY_CELL + letters[i] + EMPTY_CELL + SPACE_BETWEEN_CELLS);
        }
        System.out.print(SPACE_BETWEEN_MAPS);
    }

    public static void print(String[][] map) {

        System.out.print("\n" + PADDING);
        //LETTERS
        printLetters(map[0].length);
        System.out.print("\n");

        for (int row = 0; row < map.length; row++) {
            printRow(map, row);
            System.out.print(SPACE_BETWEEN_MAPS);
            System.out.print("\n");
        }


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
