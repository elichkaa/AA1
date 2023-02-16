package models.core;

import ui.Command;

public interface IGame {
    void processInput(Command command);

    void requestInput();

    boolean hasOutput();
}
