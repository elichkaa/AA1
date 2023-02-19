package ui.commands;

import ui.Command;

public class EndTurnCommand extends Command {
    public EndTurnCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        this.turnObserver.update("Next turn.");
        return true;
    }
}
