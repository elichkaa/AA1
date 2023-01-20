package ui;

import java.util.Stack;

public class CommandHistory {
    private Stack<Command> commandHistory;

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
