package models.core;

import ui.Command;

public interface IGame {
    boolean processInput(Command command, Player player);

    boolean hasOutput();

    String getOutput();

    void setOutput();
}
