package models.core;

import ui.Command;

public interface IGame {
    void play();

    void processInput(Command command);

    void requestInput();

    boolean hasOutput();
}
