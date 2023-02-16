package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.ErrorMessage;
import util.Regex;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SellCommand extends Command {
    private final static Pattern SELL_ALL_ARGUMENT = Pattern.compile(Regex.SELL_ALL_COMMAND_ARGS.toString());
    private final static Pattern SELL_SPECIFIC_ARGUMENTS = Pattern.compile(Regex.SELL_SPECIFIC_COMMAND_ARGS.toString());
    private final static String TOMATO_ARGUMENT = "tomato";
    private final static String CARROT_ARGUMENT = "carrot";
    private final static String MUSHROOM_ARGUMENT = "mushroom";
    private final static String SALAD_ARGUMENT = "salad";
    private final static String ALL_ARGUMENT = "all";
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
        if (vegetablesToSell.isEmpty()) {
            // sell zero vegetables
            return true;
        }
        Matcher matcher = SELL_ALL_ARGUMENT.matcher(vegetablesToSell.getFirst());
        if (matcher.matches()) {
            // sell all vegetables of player
            return true;
        }
        for (String vegetable : vegetablesToSell) {
            matcher = SELL_SPECIFIC_ARGUMENTS.matcher(vegetable);
            if (matcher.matches()) {
                switch (vegetable) {
                    case TOMATO_ARGUMENT:

                        break;
                    case MUSHROOM_ARGUMENT:

                        break;
                    case CARROT_ARGUMENT:

                        break;
                    case SALAD_ARGUMENT:

                        break;
                    case ALL_ARGUMENT:
                        this.printErrorMessage(CommandName.SELL.toString(),
                                ErrorMessage.NO_ADDITIONAL_PARAMETERS_REQUIRED.toString(),
                                ALL_ARGUMENT);
                        return false;
                }
            } else {
                this.printErrorMessage(CommandName.SELL.toString(),
                        ErrorMessage.INVALID_ARGUMENT_NAME.toString(),
                        vegetable);
                return false;
            }
        }
        return true;
    }
}
