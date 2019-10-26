package org.academiadecodigo.whiledlings.message;

import static org.academiadecodigo.whiledlings.map.Color.*;

public class Message {
    public static final String BANNER = "TERMINAL BATTELSHIP";
    public static final String WELCOME = "WELCOME MARINE! enter /help anytime to see your options";
    public static final String GENERAL_ROOM = "You are at a general room and can talk with everyone";
    // TODO: 24/10/2019 possible commands
    public static final String HELP = "here you go. The possible commands";
    public static final String PM_HELP = "Type /name message to send a pm";
    public static final String ASK_NAME = "Please insert an username:";
    public static final String ASK_COLOR = "Please select the number of a color";
    public static final String[] COLOR_OPTIONS = {ANSI_BLUE + "BLUE" + ANSI_RESET, ANSI_GREEN + "GREEN" + ANSI_RESET, ANSI_YELLOW + "YELLOW" + ANSI_RESET, ANSI_RED + "RED" + ANSI_RESET, ANSI_WHITE + "WHITE" + ANSI_RESET};
    public static final String SPECIAL_ROOM = "Welcome to room ";


    public static final String ASK_IN_POSITION_OF_SHIP = "Please select the initial position <LETTER><NUM> of ";
    public static final String ASK_FIN_POSITION_OF_SHIP = "Please select the final position <LETTER><NUM> of ";


    public static final String CARRIER = "Carrier";
    public static final String BATTLESHIP = "Battleship";
    public static final String CRUISER = "Cruiser";
    public static final String SUBMARINE = "Submarine";
    public static final String DESTROYER = "Destroyer";
    // TODO: 24/10/2019 PODIUM, POINTS,...


    public static final String NAME_EXISTS_ERROR = ANSI_RED + "This username already exists" + ANSI_RESET;
    public static final String NAME_EMPTY_ERROR = ANSI_RED + "You can't have an empty username" + ANSI_RESET;
    public static final String COMMAND_NOT_FOUND_ERROR = ANSI_RED + "This command has not been found, try /help for available commands" + ANSI_RESET;
}
