package ui;

import util.CommandName;
import util.ErrorMessage;

import java.util.List;

public abstract class Command implements ICommand {
    protected final String commandName;
    protected List<CommandArgument> commandArguments;

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

    public List<CommandArgument> getCommandArguments(){
        return this.commandArguments;
    }

    //only ui package can access setter
    protected void setCommandArguments(List<CommandArgument> arguments){
        this.commandArguments = arguments;
    }

    protected boolean isArgumentCountInvalid(String commandName, int minValidArgumentCount, int maxValidArgumentCount){
        if (this.commandArguments.size() > maxValidArgumentCount){
            this.printErrorMessage(commandName, ErrorMessage.TOO_MANY_ARGUMENTS_PROVIDED.toString(), maxValidArgumentCount);
            return true;
        } else if (this.commandArguments.size() < minValidArgumentCount){
            this.printErrorMessage(commandName, ErrorMessage.NOT_ENOUGH_ARGUMENTS_PROVIDED.toString(), minValidArgumentCount);
            return true;
        }
        return false;
    }

    protected boolean areArgumentsEmpty(){
        if (this.commandArguments.isEmpty()){
            this.printErrorMessage(commandName, ErrorMessage.NO_ARGUMENTS_PROVIDED.toString());
            return true;
        }
        return false;
    }

    protected void printErrorMessage(String commandName, String errorMessage, Object... optionalMessage){
        if (optionalMessage.length != 0) {
            System.out.printf(errorMessage + System.lineSeparator(), commandName, optionalMessage[0]);
        } else {
            System.out.printf(errorMessage + System.lineSeparator(), commandName);
        }
    }
}
