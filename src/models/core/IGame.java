package models.core;

import ui.Command;

public interface IGame {
    boolean processInput(Command command, Player player);

    Player getCurrentPlayer();

    Market getMarket();
}
