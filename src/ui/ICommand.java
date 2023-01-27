package ui;

import java.util.List;

public interface ICommand {
    boolean execute();
    void undo();
    String getCommandName();
    List<CommandArgument> getCommandArguments();
    void setCommandArguments(List<CommandArgument> arguments);
}
