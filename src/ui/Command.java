package ui;

import models.MainSystem;
import java.util.List;

public abstract class Command implements ICommand {
    protected final MainSystem system;
    protected final String commandName;
    protected List<CommandArgument> commandArguments;

    public Command(MainSystem system, String commandName) {
        this.system = system;
        this.commandName = commandName;
    }

    @Override
    public abstract boolean execute();

    @Override
    public void undo() { }

    @Override
    public String getCommandName(){
        return this.commandName;
    }

    @Override
    public List<CommandArgument> getCommandArguments(){
        return this.commandArguments;
    }

    @Override
    public void setCommandArguments(List<CommandArgument> arguments){
        this.commandArguments = arguments;
    }
}
