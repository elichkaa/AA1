package ui;

import java.util.List;

public interface ICommand {
    boolean execute();
    void undo();
}
