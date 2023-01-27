package ui;

public enum CommandNames {
    NEW("new"),
    QUIT("quit");

    private String commandName;

    CommandNames(String commandName){
        this.commandName = commandName;
    }

    @Override
    public String toString(){
        return this.commandName;
    }
}
