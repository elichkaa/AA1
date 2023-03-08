package ui.commands;

import models.core.IGame;
import models.core.Player;
import models.core.Vegetable;
import ui.Command;
import ui.CommandArgument;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SellCommand extends Command {
    private final static Pattern SELL_SPECIFIC_ARGUMENTS = Pattern.compile("\\b(tomato|mushroom|salad|carrot|all)\\b");
    private final static int MIN_VALID_ARGUMENT_COUNT = 0;
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
            return !this.isArgumentInvalid(vegetablesToSell.getFirst(),
                    SELL_SPECIFIC_ARGUMENTS,
                    INVALID_ARGUMENT_NAME,
                    vegetablesToSell.getFirst());
        }

        List<Vegetable> veggies = game.getCurrentPlayer().getGameBoard().getBarn().getRemainingVegetablesAfterSell(vegetablesToSell);
        System.out.println("You have sold " + veggies.size() + "vegetables.");

        for (String vegetable : vegetablesToSell) {
            if (!this.isArgumentInvalid(vegetable,
                    VEGETABLE_NAME_ARGUMENT,
                    INVALID_SALE_ARGUMENTS)) {
                return false;
            }
        }

        return true;
    }
}
