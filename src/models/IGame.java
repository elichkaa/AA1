package models;

import ui.Command;
import ui.CommandParseResult;

public interface IGame {
    void play();

    void processInput(Command command);

    void requestInput();

    boolean hasOutput();
}
