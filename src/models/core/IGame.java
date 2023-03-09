package models.core;

import models.core.tiles.Cultivatable;
import ui.Command;

import java.util.List;

public interface IGame {

    Player getCurrentPlayer();

    Market getMarket();

    Cultivatable getFirstRemainingTile();
}
