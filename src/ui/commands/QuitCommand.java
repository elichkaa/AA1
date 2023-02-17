package ui.commands;

import ui.Command;

public class QuitCommand extends Command {
    public QuitCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
