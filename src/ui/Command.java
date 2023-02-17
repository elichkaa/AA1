package ui;

import java.util.List;
import java.util.regex.Pattern;

public abstract class Command implements ICommand {
    protected final static Pattern NUMBER_ARGUMENT = Pattern.compile("^0*-?[0-9]\\d*$");
    protected final static Pattern VEGETABLE_NAME_ARGUMENT = Pattern.compile("\\b(tomato|mushroom|salad|carrot)\\b");
    protected static final String INVALID_COORDINATES = "Please enter integers for the coordinates.";
    protected static final String INVALID_ARGUMENT_NAME = "An argument for the command %s with the name %s doesn't exist.";
    private static final String TOO_MANY_ARGUMENTS_PROVIDED = "Too many arguments for the command %s provided. They should be maximum %d.";
    private static final String NOT_ENOUGH_ARGUMENTS_PROVIDED = "Not enough arguments for the command %s provided. They should be minimum %d.";
    private final String ERROR_PREFIX = "Error: ";
    protected final String commandName;
    protected List<CommandArgument> commandArguments;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public abstract boolean execute();

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
            this.printErrorMessage(ERROR_PREFIX + TOO_MANY_ARGUMENTS_PROVIDED, maxValidArgumentCount);
            return true;
        } else if (this.commandArguments.size() < minValidArgumentCount) {
            this.printErrorMessage(ERROR_PREFIX + NOT_ENOUGH_ARGUMENTS_PROVIDED, minValidArgumentCount);
            return true;
        }
        return false;
    }

    protected void printErrorMessage(String errorMessage, Object... optionalMessage) {
        if (optionalMessage.length != 0) {
            System.out.printf(errorMessage + System.lineSeparator(), this.commandName, optionalMessage[0]);
        } else {
            System.out.printf(errorMessage + System.lineSeparator(), this.commandName);
        }
    }

    protected boolean areArgumentsInvalid(List<String> arguments, Pattern pattern, String errorMessage) {
        for (String argument : arguments) {
            if (!pattern.matcher(argument).matches()) {
                this.printErrorMessage(this.ERROR_PREFIX + errorMessage, argument);
                return true;
            }
        }
        return false;
    }

    protected boolean isArgumentInvalid(String argument, Pattern pattern, String errorMessage, Object... optional) {
        if (!pattern.matcher(argument).matches()) {
            this.printErrorMessage(this.ERROR_PREFIX + errorMessage, optional);
            return true;
        }
        return false;
    }
}
