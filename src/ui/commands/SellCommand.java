package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.ErrorMessage;
import util.Regex;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SellCommand extends Command {
    private final static Pattern VEGETABLE_NAME_ARGUMENTS = Pattern.compile(Regex.VEGETABLE_NAME_ARGS.toString());
    private final static Pattern SELL_SPECIFIC_ARGUMENTS = Pattern.compile(Regex.SELL_SPECIFIC_COMMAND_ARGS.toString());
    private final static int MIN_VALID_ARGUMENT_COUNT = 0;
    private final static int MAX_VALID_ARGUMENT_COUNT = Integer.MAX_VALUE;
    public SellCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(CommandName.SELL.toString(), MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> vegetablesToSell = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (vegetablesToSell.size() == 1) {
            return !this.isArgumentInvalid(vegetablesToSell.getFirst(),
                    SELL_SPECIFIC_ARGUMENTS,
                    CommandName.SELL,
                    ErrorMessage.INVALID_ARGUMENT_NAME,
                    vegetablesToSell.getFirst());
        } else if (vegetablesToSell.size() > 1) {
            return !this.areArgumentsInvalid(vegetablesToSell,
                    VEGETABLE_NAME_ARGUMENTS,
                    CommandName.SELL,
                    ErrorMessage.INVALID_SALE_ARGUMENTS);
        }

        return true;
    }
}
