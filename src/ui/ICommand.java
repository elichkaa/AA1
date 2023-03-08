package ui;

import models.core.IGame;
import util.StateObserver;
import util.TurnObserver;

public interface ICommand {
    boolean execute(IGame game);

    void addStateObserver(StateObserver observer);

    void addTurnObserver(TurnObserver observer);
}
