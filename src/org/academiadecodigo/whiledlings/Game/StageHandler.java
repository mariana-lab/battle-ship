package org.academiadecodigo.whiledlings.Game;

import org.academiadecodigo.whiledlings.Player;

public interface StageHandler {
    void handle(Game game, Player player, String[][] map);
}
