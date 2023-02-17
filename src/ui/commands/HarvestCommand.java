package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.ErrorMessage;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HarvestCommand extends Command {
    private final static Pattern NUMBER_ARGUMENT = Pattern.compile("^0*-?[0-9]\\d*$");
    private final static int MIN_VALID_ARGUMENT_COUNT = 3;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;

    public HarvestCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (this.areArgumentsInvalid(commandArgs.subList(0, MAX_VALID_ARGUMENT_COUNT - 1),
                NUMBER_ARGUMENT,
                ErrorMessage.INVALID_COORDINATES)
                || this.isArgumentInvalid(commandArgs.getLast(),
                NUMBER_ARGUMENT,
                ErrorMessage.INVALID_VEGETABLE_QUANTITY,
                commandArgs.getLast())) {
            return false;
        }

        //TODO: do something with correct coordinates and quantity

        return true;
    }
}
