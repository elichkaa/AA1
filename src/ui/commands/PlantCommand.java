package ui.commands;

import ui.Command;

public class PlantCommand extends Command {
    public PlantCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
