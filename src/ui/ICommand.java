package ui;

import models.core.IGame;
import ui.observers.ActionObserver;
import ui.observers.StateObserver;
import ui.observers.TurnObserver;

public interface ICommand {
    boolean execute(IGame game);

    void addStateObserver(StateObserver observer);

    void addTurnObserver(TurnObserver observer);

    void addActionObserver(ActionObserver observer);
}
