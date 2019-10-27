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

    public static final String FINDING_GAME = "Finding a game where you can sink ships...";


    public static final String EMPTY = ANSI_RESET + " " + ANSI_YELLOW_BACKGROUND;
    public static final String DESTROYER = ANSI_YELLOW + " D E S T R O Y E R";
    public static final String DESTROYER_NUM = EMPTY + ANSI_BLACK +  " 1 " + EMPTY + ANSI_BLACK + " 2 " + ANSI_RESET;

    public static final String SUBMARINE = ANSI_YELLOW + " S U B M A R I N E" + ANSI_RESET;
    public static final String SUBMARINE_NUM = DESTROYER_NUM + EMPTY + ANSI_BLACK + " 3 " + ANSI_RESET;

    public static final String CRUISER = ANSI_YELLOW + " C R U I S E R" + ANSI_RESET;
    public static final String CRUISER_NUM = SUBMARINE_NUM;

    public static final String BATTLESHIP = ANSI_YELLOW + " B A T T L E S H I P" + ANSI_RESET;
    public static final String BATTLESHIP_NUM = CRUISER_NUM + EMPTY + ANSI_BLACK + " 4 " + ANSI_RESET;

    public static final String CARRIER = ANSI_YELLOW + " C A R R I E R" + ANSI_RESET;
    public static final String CARRIER_NUM = BATTLESHIP_NUM + EMPTY + ANSI_BLACK + " 5 " + ANSI_RESET;
    // TODO: 24/10/2019 PODIUM, POINTS,...


    public static final String NAME_EXISTS_ERROR = ANSI_RED + "This username already exists" + ANSI_RESET;
    public static final String NAME_EMPTY_ERROR = ANSI_RED + "You can't have an empty username" + ANSI_RESET;
    public static final String COMMAND_NOT_FOUND_ERROR = ANSI_RED + "This command has not been found, try /help for available commands" + ANSI_RESET;

    //GAME QUESTIONS
    public static final String ASK_DIRECTION = "Chose the direction of the ";
    public static final String ASK_POSITION = ANSI_YELLOW + "Please select the initial position of " + ANSI_RESET;

    public static final String INVALID_CELL_ERROR = ANSI_RED + "Please insert a valid position, like A2" + ANSI_RESET;
    public static final String MARKED_POSITION_ERROR = ANSI_RED + "Out of limits or overlapping boats" + ANSI_RESET;
    public static final String INVALID_MENU_ERROR = ANSI_RED + "Please select one of the options";
}
