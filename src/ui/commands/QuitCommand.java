package ui.commands;

import ui.Command;

public class QuitCommand extends Command {
    public QuitCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        this.stateObserver.update("Game ended.");
        return true;
    }
}
