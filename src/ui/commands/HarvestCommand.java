package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.ErrorMessage;
import util.Regex;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HarvestCommand extends Command {
    private final static Pattern COORDINATES_ARGUMENT = Pattern.compile(Regex.COORDINATES.toString());
    private final static Pattern QUANTITY_ARGUMENT = Pattern.compile(Regex.WHOLE_NUMBER.toString());
    private final static int MIN_VALID_ARGUMENT_COUNT = 3;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;

    public HarvestCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(CommandName.HARVEST.toString(), MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (this.areArgumentsInvalid(commandArgs.subList(0, MAX_VALID_ARGUMENT_COUNT - 1),
                COORDINATES_ARGUMENT,
                CommandName.HARVEST,
                ErrorMessage.INVALID_COORDINATES)
                || this.isArgumentInvalid(commandArgs.getLast(),
                QUANTITY_ARGUMENT,
                CommandName.HARVEST,
                ErrorMessage.INVALID_VEGETABLE_QUANTITY,
                commandArgs.getLast())) {
            return false;
        }

        //TODO: do something with correct coordinates and quantity

        return true;
    }
}
