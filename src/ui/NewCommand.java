package ui;

import java.util.List;

public class NewCommand extends Command {
    public NewCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        return true;
    }
}
