package models.core;

import ui.Command;

public interface IGame {
    void processInput(Command command, Player player);

    boolean hasOutput();

    String getOutput();

    void setOutput();
}
