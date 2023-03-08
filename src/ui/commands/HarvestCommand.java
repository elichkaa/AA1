package ui.commands;

import models.core.IGame;
import models.core.Player;
import ui.Command;
import ui.CommandArgument;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HarvestCommand extends Command {
    private final static int MIN_VALID_ARGUMENT_COUNT = 3;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;
    private static final String INVALID_VEGETABLE_QUANTITY = "Please enter zero or positive amount of vegetables.";
    protected final static Pattern VEGETABLE_QUANTITY_ARGUMENT = Pattern.compile("^0*?[0-9]\\d*$");

    public HarvestCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(IGame game) {
        if (this.isArgumentCountInvalid(MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (this.areCoordinatesInvalid(commandArgs.subList(0, MAX_VALID_ARGUMENT_COUNT - 1))
                || (this.isArgumentInvalid(commandArgs.getLast(),
                VEGETABLE_QUANTITY_ARGUMENT,
                INVALID_VEGETABLE_QUANTITY,
                commandArgs.getLast()) || !canParseVegetableQuantityToInt(commandArgs.getLast()))) {
            return false;
        }

        //TODO: do something with correct coordinates and quantity

        return true;
    }

    private boolean canParseVegetableQuantityToInt(String quantity) {
        try {
            Integer.parseInt(quantity);
            return true;
        } catch (NumberFormatException exception) {
            this.printErrorMessage(INVALID_VEGETABLE_QUANTITY);
            return false;
        }
    }
}
