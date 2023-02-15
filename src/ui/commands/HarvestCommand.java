package ui.commands;

import ui.Command;

public class HarvestCommand extends Command {
    public HarvestCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
