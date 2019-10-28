package org.academiadecodigo.whiledlings.Game;

import org.academiadecodigo.whiledlings.Game.handler.FinalHandler;
import org.academiadecodigo.whiledlings.Game.handler.RoundsHandler;
import org.academiadecodigo.whiledlings.Game.handler.SetupHandler;
import org.academiadecodigo.whiledlings.message.Message;

public enum GameStage {
    SETUP(new SetupHandler(), Message.SETUP_INITIAL, Message.SETUP_FINAL),
    ROUNDS(new RoundsHandler(), Message.ROUNDS_INITIAL, Message.ROUNDS_FINAL),
    FINAL(new FinalHandler(), Message.FINAL_INITIAL, Message.FINAL_FINAL);

    private StageHandler stageHandler;
    private String initialMessage;
    private String finalMessage;

    GameStage(StageHandler stageHandler, String initialMessage, String finalMessage) {
        this.stageHandler = stageHandler;
        this.initialMessage = initialMessage;
        this.finalMessage = finalMessage;
    }

    public StageHandler getStageHandler() {
        return stageHandler;
    }

    public String getInitialMessage() {
        return this.initialMessage;
    }
    public String getFinalMessage() {
        return this.finalMessage;
    }
}
