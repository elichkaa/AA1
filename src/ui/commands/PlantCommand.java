package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.ErrorMessage;
import util.Regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlantCommand extends Command {
    private final static Pattern COORDINATES_ARGUMENT = Pattern.compile(Regex.COORDINATES.toString());
    private final static Pattern VEGETABLE_NAME_ARGUMENT = Pattern.compile(Regex.VEGETABLE_NAME_ARGS.toString());
    private final static String TOMATO_ARGUMENT = "tomato";
    private final static String CARROT_ARGUMENT = "carrot";
    private final static String MUSHROOM_ARGUMENT = "mushroom";
    private final static String SALAD_ARGUMENT = "salad";
    private final static int MIN_VALID_ARGUMENT_COUNT = 3;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;

    public PlantCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(CommandName.BUY.toString(), MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        List<String> coordinates = commandArgs.subList(0, MAX_VALID_ARGUMENT_COUNT - 1);
        if (!this.areArgumentsValid(coordinates, COORDINATES_ARGUMENT, CommandName.PLANT, ErrorMessage.INVALID_COORDINATES)) {
            return false;
        }
        if (!VEGETABLE_NAME_ARGUMENT.matcher(commandArgs.getLast()).matches()) {
            this.printErrorMessage(CommandName.PLANT.toString(), ErrorMessage.INVALID_VEGETABLE_NAME.toString(), commandArgs.getLast());
            return false;
        }

        // TODO: redirect correct arguments to logic unit

        return true;
    }
}
