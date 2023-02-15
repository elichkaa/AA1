package ui.commands;

import ui.Command;

public class ShowCommand extends Command {
    public ShowCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return true;
    }
}
