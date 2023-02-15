package ui;

import java.util.List;

public abstract class Command implements ICommand {
    protected final String commandName;
    protected List<CommandArgument<?>> commandArguments;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public abstract boolean execute();

    @Override
    public void undo() { }

    public String getCommandName(){
        return this.commandName;
    }

    public List<CommandArgument<?>> getCommandArguments(){
        return this.commandArguments;
    }

    //only ui package can access setter
    protected void setCommandArguments(List<CommandArgument<?>> arguments){
        this.commandArguments = arguments;
    }
}
