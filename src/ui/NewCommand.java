package ui;

import models.MainSystem;

import java.util.List;

public class NewCommand extends Command {
    public NewCommand(MainSystem system, String commandName) {
        super(system, commandName);
    }

    @Override
    public boolean execute() {
        return true;
    }
}
