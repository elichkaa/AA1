package ui.commands;

import ui.Command;
import ui.CommandArgument;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class BuyCommand extends Command {
    private final static int MIN_VALID_ARGUMENT_COUNT = 2;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;
    private final static int FIRST_COORDINATE_INDEX = 1;
    private final static int SECOND_COORDINATE_INDEX = 2;
    private final static int VEGETABLE_NAME_INDEX = 1;
    private final static String WHITESPACE = " ";
    private final static String LAND_ARGUMENT = "land";
    private final static String VEGETABLE_ARGUMENT = "vegetable";
    private final static String INVALID_VEGETABLE_NAME = "Invalid vegetable name for %s command. A vegetable with the name %s doesn't exist.";
    private final static String SECOND_COORDINATE_MISSING = "The %s command is missing its second coordinate.";

    public BuyCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }
        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue).
                collect(Collectors.toCollection(LinkedList::new));

        switch (commandArgs.getFirst()) {
            case LAND_ARGUMENT -> {
                if (commandArgs.size() < MAX_VALID_ARGUMENT_COUNT) {
                    this.printErrorMessage(this.ERROR_PREFIX + SECOND_COORDINATE_MISSING, this.commandName + WHITESPACE + LAND_ARGUMENT);
                    return false;
                }
                if (this.areCoordinatesInvalid(commandArgs.subList(FIRST_COORDINATE_INDEX, SECOND_COORDINATE_INDEX + 1))) {
                    return false;
                }
            }
            case VEGETABLE_ARGUMENT -> {
                String vegetableName = commandArgs.get(VEGETABLE_NAME_INDEX);
                if (this.isArgumentInvalid(vegetableName,
                        VEGETABLE_NAME_ARGUMENT,
                        INVALID_VEGETABLE_NAME,
                        vegetableName)) {
                    return false;
                }
            }
            default -> {
                this.printErrorMessage(this.commandName, INVALID_ARGUMENT_NAME, commandArgs.getFirst());
                return false;
            }
        }

        return true;
    }
}
