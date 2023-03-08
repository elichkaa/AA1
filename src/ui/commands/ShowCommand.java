package ui.commands;

import models.core.IGame;
import ui.Command;
import ui.CommandArgument;
import util.MessagePrinter;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShowCommand extends Command {
    private final static Pattern ARGUMENTS_PATTERN = Pattern.compile("(barn|market|board)");
    private final static String BARN_ARGUMENT = "barn";
    private final static String BOARD_ARGUMENT = "board";
    private final static String MARKET_ARGUMENT = "market";
    private final static int MIN_VALID_ARGUMENT_COUNT = 1;
    private final static int MAX_VALID_ARGUMENT_COUNT = 1;

    public ShowCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(IGame game) {
        if (this.isArgumentCountInvalid(MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (this.isArgumentInvalid(commandArgs.getLast(),
                ARGUMENTS_PATTERN,
                INVALID_ARGUMENT_NAME,
                commandArgs.getFirst())) {
            return false;
        }

        switch (commandArgs.getFirst()) {
            case BOARD_ARGUMENT -> MessagePrinter.printOutput(game.getCurrentPlayer().getGameBoard().toString());
            case BARN_ARGUMENT -> MessagePrinter.printOutput(game.getCurrentPlayer().getGameBoard()
                    .getBarn().getBarnInformation(game.getCurrentPlayer().getGold()));
            case MARKET_ARGUMENT -> MessagePrinter.printOutput(game.getMarket().toString());
        }

        return true;
    }
}
