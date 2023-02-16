package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.CoreString;
import util.ErrorMessage;
import util.Regex;

import java.util.LinkedList;
import java.util.regex.Matcher;
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
    private final static String TOMATO_ARGUMENT = "tomato";
    private final static String CARROT_ARGUMENT = "carrot";
    private final static String MUSHROOM_ARGUMENT = "mushroom";
    private final static String SALAD_ARGUMENT = "salad";

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
        Matcher matcher;
        if (commandArgs.getFirst().equals(LAND_ARGUMENT)) {
            if (commandArgs.size() < MAX_VALID_ARGUMENT_COUNT) {
                this.printErrorMessage(CommandName.BUY.toString() + CoreString.WHITESPACE_STRING + LAND_ARGUMENT,
                        ErrorMessage.SECOND_COORDINATE_MISSING.toString());
                return false;
            }
            if (COORDINATES_ARGUMENT.matcher(commandArgs.get(FIRST_COORDINATE_INDEX)).matches() &&
                    COORDINATES_ARGUMENT.matcher(commandArgs.get(SECOND_COORDINATE_INDEX)).matches()) {
                // do something with coordinates idk
            } else {
                this.printErrorMessage(CommandName.BUY.toString(), ErrorMessage.INVALID_COORDINATES.toString());
                return false;
            }

        } else if (commandArgs.getFirst().equals(VEGETABLE_ARGUMENT)) {
            String vegetableName = commandArgs.get(VEGETABLE_NAME_INDEX);
            matcher = VEGETABLE_NAME_ARGUMENT.matcher(vegetableName);
            if (matcher.matches()) {
                // TODO: simplify, maybe have a method in the logic units that converts the String to the specific object type, dont do it here
                switch (vegetableName) {
                    case TOMATO_ARGUMENT:

                        break;
                    case MUSHROOM_ARGUMENT:

                        break;
                    case CARROT_ARGUMENT:

                        break;
                    case SALAD_ARGUMENT:

                        break;
                }} else {
                this.printErrorMessage(CommandName.BUY.toString(), ErrorMessage.INVALID_VEGETABLE_NAME.toString(), vegetableName);
                return false;
            }
        } else {
            this.printErrorMessage(CommandName.BUY.toString(), ErrorMessage.INVALID_ARGUMENT_NAME.toString(), commandArgs.getFirst());
            return false;
        }
        return true;
    }
}
