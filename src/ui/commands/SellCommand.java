package ui.commands;

import models.core.IGame;
import ui.Command;
import ui.CommandArgument;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SellCommand extends Command {
    private final static Pattern SELL_SPECIFIC_ARGUMENTS = Pattern.compile("\\b(tomato|mushroom|salad|carrot|all)\\b");
    private final static String ALL_ARGUMENT = "all";
    private final static int MIN_VALID_ARGUMENT_COUNT = 1;
    private final static int MAX_VALID_ARGUMENT_COUNT = Integer.MAX_VALUE;
    private final static String INVALID_SALE_ARGUMENTS = "An argument for the command %s with the name %s doesn't exist or the argument all was entered alongside other arguments.";

    public SellCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(IGame game) {
        if (this.isArgumentCountInvalid(MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> vegetablesToSell = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (vegetablesToSell.size() == 1) {
            if (this.isArgumentInvalid(vegetablesToSell.getFirst(),
                    SELL_SPECIFIC_ARGUMENTS,
                    INVALID_ARGUMENT_NAME,
                    vegetablesToSell.getFirst())) {
                return false;
            }
            if (vegetablesToSell.getFirst().equals(ALL_ARGUMENT)) {
                return game.getCurrentPlayer().sellAllVegetables(game.getMarket());
            }
        }

        for (String vegetable : vegetablesToSell.stream().toList()) {
            if (this.isArgumentInvalid(vegetable,
                    VEGETABLE_NAME_ARGUMENT,
                    INVALID_SALE_ARGUMENTS)) {
                return false;
            }
        }

        return game.getCurrentPlayer().sellSpecificVegetables(game.getMarket(), vegetablesToSell);
    }
}
