package ui;

import java.util.List;

public class QuitCommand extends Command {
    public QuitCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return true;
    }
}
