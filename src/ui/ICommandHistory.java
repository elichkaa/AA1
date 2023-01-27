package ui;

public interface ICommandHistory {
    void addCommand(Command command);
    Command removeCommand();
    boolean isEmpty();
}
