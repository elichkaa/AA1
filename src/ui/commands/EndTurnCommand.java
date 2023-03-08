package ui.commands;

import models.core.IGame;
import ui.Command;

public class EndTurnCommand extends Command {
    public EndTurnCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(IGame game) {
        this.turnObserver.update("Next turn.");
        return true;
    }
}
