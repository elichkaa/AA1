package ui.commands;

import ui.Command;

public class SellCommand extends Command {
    public SellCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
