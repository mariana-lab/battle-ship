package org.academiadecodigo.whiledlings.server;

public enum Command {
    LIST("/list"),
    PLAY("/play"),
    MESSAGE("/"),
    QUIT("/quit");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean exists(String str){
        for (Command c: values()) {
            if(c.getCommand().equals(str)){
                return true;
            }
        }
        return false;
    }

    public String getCommand() {
        return this.command;
    }
}
