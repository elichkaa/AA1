package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.CoreString;
import util.ErrorMessage;
import util.Regex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BuyCommand extends Command {
    private final static Pattern COORDINATES_ARGUMENT = Pattern.compile(Regex.COORDINATES.toString());
    private final static Pattern VEGETABLE_NAME_ARGUMENT = Pattern.compile(Regex.VEGETABLE_NAME_ARGS.toString());
    private final static int MIN_VALID_ARGUMENT_COUNT = 2;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;
    private final static int FIRST_COORDINATE_INDEX = 1;
    private final static int SECOND_COORDINATE_INDEX = 2;
    private final static int VEGETABLE_NAME_INDEX = 1;
    private final static String LAND_ARGUMENT = "land";
    private final static String VEGETABLE_ARGUMENT = "vegetable";

    public BuyCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(CommandName.BUY.toString(), MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }
        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue).
                collect(Collectors.toCollection(LinkedList::new));

        switch (commandArgs.getFirst()) {
            case LAND_ARGUMENT -> {
                if (commandArgs.size() < MAX_VALID_ARGUMENT_COUNT) {
                    this.printErrorMessage(CommandName.BUY.toString() + CoreString.WHITESPACE_STRING + LAND_ARGUMENT,
                            ErrorMessage.SECOND_COORDINATE_MISSING.toString());
                    return false;
                }
                if (this.areArgumentsInvalid(commandArgs.subList(FIRST_COORDINATE_INDEX, SECOND_COORDINATE_INDEX + 1),
                        COORDINATES_ARGUMENT,
                        CommandName.BUY,
                        ErrorMessage.INVALID_COORDINATES)) {
                    return false;
                }
            }
            case VEGETABLE_ARGUMENT -> {
                String vegetableName = commandArgs.get(VEGETABLE_NAME_INDEX);
                if (this.isArgumentInvalid(vegetableName,
                        VEGETABLE_NAME_ARGUMENT,
                        CommandName.BUY,
                        ErrorMessage.INVALID_VEGETABLE_NAME,
                        vegetableName)) {
                    return false;
                }
            }
            default -> {
                this.printErrorMessage(CommandName.BUY.toString(), ErrorMessage.INVALID_ARGUMENT_NAME.toString(), commandArgs.getFirst());
                return false;
            }
        }

        return true;
    }
}
