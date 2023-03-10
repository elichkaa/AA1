package ui;

import models.core.IGame;
import ui.observers.ActionObserver;
import util.ErrorPrinter;
import ui.observers.StateObserver;
import ui.observers.TurnObserver;

import java.util.List;
import java.util.regex.Pattern;

public abstract class Command implements ICommand {
    protected final static Pattern COORDINATE_ARGUMENT = Pattern.compile("^0*-?[0-9]\\d*$");
    protected final static Pattern VEGETABLE_NAME_ARGUMENT = Pattern.compile("\\b(tomato|mushroom|salad|carrot)\\b");
    protected static final String INVALID_COORDINATES = "Coordinates are invalid. Please input numbers between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE;
    protected static final String INVALID_ARGUMENT_NAME = "An argument for the command %s with the name %s doesn't exist.";
    private static final String TOO_MANY_ARGUMENTS_PROVIDED = "Too many arguments for the command %s provided. They should be maximum %d.";
    private static final String NOT_ENOUGH_ARGUMENTS_PROVIDED = "Not enough arguments for the command %s provided. They should be minimum %d.";
    protected final String commandName;
    protected List<CommandArgument> commandArguments;
    protected StateObserver stateObserver;
    protected TurnObserver turnObserver;
    protected ActionObserver actionObserver;
    public Command(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public abstract boolean execute(IGame game);

    public String getCommandName() {
        return this.commandName;
    }

    public List<CommandArgument> getCommandArguments(){
        return this.commandArguments;
    }

    protected void setCommandArguments(List<CommandArgument> arguments){
        this.commandArguments = arguments;
    }

    protected boolean isArgumentCountInvalid(int minValidArgumentCount, int maxValidArgumentCount) {
        if (this.commandArguments.size() > maxValidArgumentCount) {
            this.printErrorMessage(TOO_MANY_ARGUMENTS_PROVIDED, maxValidArgumentCount);
            return true;
        } else if (this.commandArguments.size() < minValidArgumentCount) {
            this.printErrorMessage(NOT_ENOUGH_ARGUMENTS_PROVIDED, minValidArgumentCount);
            return true;
        }
        return false;
    }

    protected void printErrorMessage(String errorMessage, Object... optionalMessage) {
        if (optionalMessage.length != 0) {
            ErrorPrinter.print(errorMessage, this.commandName, optionalMessage[0]);
        } else {
            ErrorPrinter.print(errorMessage, this.commandName);
        }
    }

    protected boolean areCoordinatesInvalid(List<String> coordinates) {
        for (String coordinate : coordinates) {
            if (!Command.COORDINATE_ARGUMENT.matcher(coordinate).matches() || !this.canParseInteger(coordinate)) {
                this.printErrorMessage(INVALID_COORDINATES, coordinate);
                return true;
            }
        }
        return false;
    }

    protected boolean isArgumentInvalid(String argument, Pattern pattern, String errorMessage, Object... optional) {
        if (!pattern.matcher(argument).matches()) {
            this.printErrorMessage(errorMessage, optional);
            return true;
        }
        return false;
    }

    private boolean canParseInteger(String coordinate) {
        try {
            Integer.parseInt(coordinate);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public void addStateObserver(StateObserver observer) {
        this.stateObserver = observer;
    }

    public void addTurnObserver(TurnObserver observer) {
        this.turnObserver = observer;
    }

    @Override
    public void addActionObserver(ActionObserver observer) {
        this.actionObserver = observer;
    }

    public boolean updateActionsObserverAndReturnOperationExecutionResult(boolean action) {
        if (action) {
            this.actionObserver.update("Successful action.");
            return true;
        }
        return false;
    }
}
