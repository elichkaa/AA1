package ui;

import models.Program;

public abstract class Command implements ICommand {
    protected final Program program;

    public Command(Program program){
        this.program = program;
    }

    @Override
    public abstract void execute();

    @Override
    public void undo() {
        // idk if needed
    }
}
