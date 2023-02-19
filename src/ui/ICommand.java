package ui;

import util.StateObserver;
import util.TurnObserver;

public interface ICommand {
    boolean execute();

    void addStateObserver(StateObserver observer);

    void addTurnObserver(TurnObserver observer);
}
