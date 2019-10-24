package org.academiadecodigo.whiledlings.battleship;

public class MapHandler {

    public static final String EMPTY_CELL = " ";
    public static final String SPACE_BETWEEN_CELLS = " ";
    public static final String SPACE_BETWEEN_MAPS = "   ";
    public static final String PADDING = "  " + SPACE_BETWEEN_CELLS;

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001b[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001b[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static String[][] map = new String[10][10];
    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
    public static String LEFT_WALL = "[";
    public static String RIGHT_WALL = "]";


    public static void main(String[] args) {


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[j][i] = EMPTY_CELL;
            }
        }
        paintCell(getColFromLetter("B"), getRowFromNumber("4"), PlayType.MARK.symbol);
        paintCell(getColFromLetter("C"), getRowFromNumber("4"), PlayType.MISS.symbol);
        paint(map, map);
    }

    public static void paint(String[][] map1, String[][] map2) {

        System.out.print(PADDING);

        //LETTERS
        printLetters(map1[0].length);
        System.out.print(SPACE_BETWEEN_CELLS + SPACE_BETWEEN_CELLS);
        printLetters(map2[0].length);
        System.out.print("\n");


        for (int row = 0; row < map1.length; row++) {
            printRow(row, map1);
            System.out.print(SPACE_BETWEEN_MAPS);
            printRow(row, map2);
            System.out.print("\n");

        }


    }

    private static void printRow(int row, String[][] map) {
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

    public static void paint(String[][] map) {

        System.out.print(PADDING);
        //LETTERS
        printLetters(map[0].length);
        for (int row = 0; row < map.length; row++) {
            printRow(row, map);
            System.out.print(SPACE_BETWEEN_MAPS);
            System.out.print("\n");
        }


    }

    public static void paintCell(int col, int row, String symbol) {
        map[col][row] = symbol;
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
