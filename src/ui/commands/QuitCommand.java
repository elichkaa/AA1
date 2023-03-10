package ui.commands;

import models.core.IGame;
import ui.Command;

public class QuitCommand extends Command {
    public QuitCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(IGame game) {
        this.stateObserver.update("Game ended.");
        return true;
    }
}
