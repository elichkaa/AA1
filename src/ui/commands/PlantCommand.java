package ui.commands;

import models.core.IGame;
import models.core.Player;
import ui.Command;
import ui.CommandArgument;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class PlantCommand extends Command {
    private final static int MIN_VALID_ARGUMENT_COUNT = 3;
    private final static int MAX_VALID_ARGUMENT_COUNT = 3;
    private final static String INVALID_VEGETABLE_NAME = "Invalid vegetable name for %s command. A vegetable with the name %s doesn't exist.";

    public PlantCommand(String commandName) {
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
                || this.isArgumentInvalid(commandArgs.getLast(),
                VEGETABLE_NAME_ARGUMENT,
                INVALID_VEGETABLE_NAME,
                commandArgs.getLast())) {
            return false;
        }

        int xCoordinateOfTile = Integer.parseInt(commandArgs.get(0));
        int yCoordinateOfTile = Integer.parseInt(commandArgs.get(1));
        String vegetableName = commandArgs.get(2);

        return game.getCurrentPlayer().plantVegetable(vegetableName, xCoordinateOfTile, yCoordinateOfTile);
    }
}
