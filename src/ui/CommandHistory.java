package ui;

import java.util.Stack;

public class CommandHistory implements ICommandHistory {
    private Stack<Command> commandHistory;

    public CommandHistory(){
        this.commandHistory = new Stack<>();
    }

    public void addCommand(Command command){
        this.commandHistory.push(command);
    }

    public Command removeCommand(){
        return this.commandHistory.pop();
    }

    public boolean isEmpty(){
        return this.commandHistory.isEmpty();
    }
}
