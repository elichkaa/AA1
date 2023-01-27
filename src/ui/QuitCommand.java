package ui;

import models.MainSystem;

import java.util.List;

public class QuitCommand extends Command {
    public QuitCommand(MainSystem system, String commandName) {
        super(system, commandName);
    }

    @Override
    public boolean execute() {
        this.system.setSystemState(false);
        return true;
    }
}
