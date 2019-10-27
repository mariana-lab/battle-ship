package org.academiadecodigo.whiledlings.map;

import java.util.HashMap;

public class Color {

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

    public static final HashMap<String,String> colorOptions = createColorOptions();

    private static HashMap<String, String> createColorOptions() {
        HashMap<String,String> options = new HashMap<>();
        options.put(ANSI_PURPLE + "PURPLE" + ANSI_RESET, ANSI_PURPLE);
        options.put(ANSI_BLUE + "BLUE" + ANSI_RESET, ANSI_BLUE);
        options.put(ANSI_GREEN + "GREEN" + ANSI_RESET, ANSI_GREEN);
        options.put(ANSI_YELLOW + "YELLOW" + ANSI_RESET, ANSI_YELLOW);
        options.put(ANSI_RED + "RED" + ANSI_RESET, ANSI_RED);
        options.put(ANSI_WHITE + "WHITE" + ANSI_RESET, ANSI_WHITE);
        return options;
    }
}
