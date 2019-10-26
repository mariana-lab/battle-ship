package org.academiadecodigo.whiledlings.command;

import org.academiadecodigo.whiledlings.command.handler.*;

public enum Command {
    // TODO: 26/10/2019 mandar mensagens ao oponente, ver progresso, ver podium
    INVALID("", new InvalidHandler()),
    LIST("list", new ListHandler()),
    HELP("help", new HelpHandler()),
    PLAY("play", new GameHandler()),
    MESSAGE("", new MessageHandler()),
    WHISPER("", new WhisperHandler()),
    QUIT("quit", new QuitHandler()),
    EXIT("exit", new ExitHandler());

    private String commandMessage;
    private CommandHandler commandHandler;

    Command(String commandMessage, CommandHandler commandHandler) {
        this.commandMessage = commandMessage;
        this.commandHandler = commandHandler;
    }

    public static boolean exists(String str){
        for (Command c: values()) {
            if(c.getCommandMessage().equals(str)){
                return true;
            }
        }
        return false;
    }

    public static Command getCommand(String str){

        String[] split = str.split("/");

        //if its size's == 1, its a broadcast, no /
        if (split.length <= 1) {
            return MESSAGE;
        }
        //if it has /name message
        if(split[1].split(" ").length > 1){
            return WHISPER;
        }

        //command message
        for (int i = 0; i < values().length; i++) {
            if(split[1].equals(values()[i].commandMessage)){
                return values()[i];
            }
        }

        return INVALID;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public String getCommandMessage() {
        return this.commandMessage;
    }
}
