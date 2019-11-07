package org.academiadecodigo.whiledlings.Game.handler;

import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.whiledlings.Game.Game;
import org.academiadecodigo.whiledlings.Game.StageHandler;
import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.map.MapHandler;
import org.academiadecodigo.whiledlings.message.Message;
import org.academiadecodigo.whiledlings.message.QuestionHandler;

import static org.academiadecodigo.whiledlings.message.Message.*;

public class RoundsHandler implements StageHandler {
    @Override
    public void handle(Game game, Player player, String[][] map) {

        player.send(ROUNDS_START);

        while (!game.hasCompletedMap()) {
            String[][] oponentMap = game.getOponent(player).getMap();

            player.send(MapHandler.build(oponentMap,map));
            String position = askPosition(player, oponentMap, map);

            // TODO: 28/10/2019 better this
            player.send(MapHandler.hit(oponentMap, MapHandler.getColFromLetter(position.split("")[0]),MapHandler.getRowFromNumber(position.substring(1))) ? HIT : MISS);


            //show copy
            String[][] oponentMapCopy = new String[oponentMap.length][oponentMap[1].length];
            for(int i=0; i<oponentMap.length; i++)
                for(int j=0; j<oponentMap[i].length; j++)
                    oponentMapCopy[i][j]=oponentMap[i][j];

            // TODO: 28/10/2019 refactor paint cell
            //paint copy
            MapHandler.paintCell(oponentMapCopy, MapHandler.getColFromLetter(position.split("")[0]), MapHandler.getRowFromNumber(position.substring(1)), MapHandler.MoveType.TRY.getSymbol());
            player.send(MapHandler.build(oponentMapCopy,map));



            //now we wait for the oponent
            game.waitOrNotifyOtherPlayer(player);
            //show result


        }
        //while !maps completed
        //print both maps for player
        //ask for ball
        //wait for response of other

    }

    private String askPosition(Player player, String[][] oponentMap, String[][] map) {

        StringSetInputScanner positionQuestion = QuestionHandler.buildQuestion(ASK_HIT_POSITION + "\n", INVALID_CELL_ERROR, MapHandler.positions);
        String position = player.ask(positionQuestion);

        if (!MapHandler.canHitOrMiss(oponentMap, position)) {
            player.send(Message.MARKED_POSITION_ERROR);
            return askPosition(player, oponentMap, map);
        }
        return position;
    }

}
