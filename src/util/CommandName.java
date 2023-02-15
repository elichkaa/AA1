package util;

public enum CommandName {
    SHOW("show"),
    QUIT("quit");

    private final String commandName;

    CommandName(String commandName){
        this.commandName = commandName;
    }

    @Override
    public String toString(){
        return this.commandName;
    }
}
