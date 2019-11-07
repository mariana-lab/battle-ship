package org.academiadecodigo.whiledlings.Game.handler;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.whiledlings.Game.Game;
import org.academiadecodigo.whiledlings.Game.StageHandler;
import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.map.Boat;
import org.academiadecodigo.whiledlings.map.BoatType;
import org.academiadecodigo.whiledlings.map.Direction;
import org.academiadecodigo.whiledlings.map.MapHandler;
import org.academiadecodigo.whiledlings.message.QuestionHandler;

import static org.academiadecodigo.whiledlings.message.Message.*;
import static org.academiadecodigo.whiledlings.message.Message.INVALID_MENU_ERROR;
import static org.academiadecodigo.whiledlings.message.Message.MARKED_POSITION_ERROR;

public class SetupHandler implements StageHandler {

    @Override
    public void handle(Game game, Player player, String[][] map) {
        markBoats(player,map);
        player.send(COMPLETION_MAP + game.getOponent(player).getUsername());
        game.waitOrNotifyOtherPlayer(player);
    }

    private void markBoats(Player player, String[][] map) {

        for (BoatType boatType : BoatType.values()) {

            Boat boat = askBoatPosition(player, map, boatType);
            MapHandler.paintBoat(map, boat);

            System.out.println(MapHandler.buildInitial(map, BoatType.getInitialBoatsInfo(boatType, ASK_BOAT_POSITION + boatType.getName())));
            player.send(MapHandler.buildInitial(map, BoatType.getInitialBoatsInfo(boatType, ASK_BOAT_POSITION + boatType.getName())));
        }


    }

    private Boat askBoatPosition(Player player, String[][] map, BoatType boatType) {
        // TODO: 27/10/2019 SEPERATE THE LOGIC
        // TODO: 27/10/2019 CHANGE COLOR OF BOATS
        StringSetInputScanner positionQuestion = QuestionHandler.buildQuestion(ASK_BOAT_POSITION + boatType.getName() + "\n", INVALID_CELL_ERROR, MapHandler.positions);
        MenuInputScanner directionQuestion = QuestionHandler.buildQuestion(ASK_BOAT_DIRECTION + boatType.getName(), INVALID_MENU_ERROR, Direction.getAll());

        //initial position of boat
        player.send(MapHandler.buildInitial(map, boatType));
        String position = player.ask(positionQuestion);


        //direction of boat
        int directionInt = player.ask(directionQuestion);
        Direction direction = Direction.values()[directionInt - 1];

        //verification of limits and other boats
        if (!MapHandler.canMark(map, position, direction, boatType)) {
            player.send(MARKED_POSITION_ERROR);
            return askBoatPosition(player, map, boatType);
        }

        return new Boat(position, direction, boatType, MapHandler.MoveType.MARK.getSymbol());
    }
}
