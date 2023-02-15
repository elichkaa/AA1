package util;

public enum CommandName {
    SHOW("show"),
    SELL("sell"),
    PLANT("plant"),
    HARVEST("harvest"),
    BUY("buy");

    private final String commandName;

    CommandName(String commandName){
        this.commandName = commandName;
    }

    @Override
    public String toString(){
        return this.commandName;
    }
}
