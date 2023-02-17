package ui;

import util.ErrorMessage;

import java.util.List;
import java.util.regex.Pattern;

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

    protected boolean isArgumentCountInvalid(int minValidArgumentCount, int maxValidArgumentCount) {
        if (this.commandArguments.size() > maxValidArgumentCount) {
            this.printErrorMessage(this.commandName, ErrorMessage.TOO_MANY_ARGUMENTS_PROVIDED.toString(), maxValidArgumentCount);
            return true;
        } else if (this.commandArguments.size() < minValidArgumentCount) {
            this.printErrorMessage(this.commandName, ErrorMessage.NOT_ENOUGH_ARGUMENTS_PROVIDED.toString(), minValidArgumentCount);
            return true;
        }
        return false;
    }

    protected void printErrorMessage(String errorMessage, Object... optionalMessage) {
        if (optionalMessage.length != 0) {
            System.out.printf(errorMessage + System.lineSeparator(), commandName, optionalMessage[0]);
        } else {
            System.out.printf(errorMessage + System.lineSeparator(), commandName);
        }
    }

    protected boolean areArgumentsInvalid(List<String> arguments, Pattern pattern, ErrorMessage errorMessage) {
        for (String argument : arguments) {
            if (!pattern.matcher(argument).matches()) {
                this.printErrorMessage(this.commandName, errorMessage.toString(), argument);
                return true;
            }
        }
        return false;
    }

    protected boolean isArgumentInvalid(String argument, Pattern pattern, ErrorMessage errorMessage, Object... optional) {
        if (!pattern.matcher(argument).matches()) {
            this.printErrorMessage(this.commandName, errorMessage.toString(), optional);
            return true;
        }
        return false;
    }
}
