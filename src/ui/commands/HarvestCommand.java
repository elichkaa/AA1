package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.ErrorMessage;
import util.Regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
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
        if (this.isArgumentCountInvalid(CommandName.BUY.toString(), MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        List<String> coordinates = commandArgs.subList(0, 1);
        Matcher matcher;
        for (String coordinate : coordinates) {
            if (!COORDINATES_ARGUMENT.matcher(coordinate).matches()) {
                this.printErrorMessage(CommandName.BUY.toString(), ErrorMessage.INVALID_COORDINATES.toString());
                return false;
            }
        }
        if (!QUANTITY_ARGUMENT.matcher(commandArgs.getLast()).matches()) {
            this.printErrorMessage(CommandName.BUY.toString(), ErrorMessage.INVALID_VEGETABLE_QUANTITY.toString());
            return false;
        }

        //TODO: do something with correct coordinates and quantity

        return true;
    }
}
