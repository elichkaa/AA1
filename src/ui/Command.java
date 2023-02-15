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

    protected boolean areArgumentsValid(int invalidArgumentCount, int validArgumentCount){
        if (this.commandArguments.isEmpty()){
            this.printErrorMessage(ErrorMessage.NO_ARGUMENTS_PROVIDED.toString());
            return false;
        } else if (this.commandArguments.size() >= invalidArgumentCount){
            this.printErrorMessage(ErrorMessage.TOO_MANY_ARGUMENTS_PROVIDED.toString(), validArgumentCount);
            return false;
        }
        return true;
    }

    protected void printErrorMessage(String errorMessage, Object... optionalMessage){
        if (optionalMessage.length != 0) {
            System.out.printf(errorMessage + System.lineSeparator(), CommandName.SHOW, optionalMessage[0]);
        } else {
            System.out.printf(errorMessage + System.lineSeparator(), CommandName.SHOW);
        }
    }
}
