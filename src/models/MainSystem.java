package models;

import ui.Command;
import ui.CommandHistory;
import ui.NewCommand;
import ui.QuitCommand;
import ui.CommandNames;
import util.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainSystem {
    private final CommandHistory commandHistory = new CommandHistory();
    private boolean systemState = false;
    private List<Command> allCommands = new ArrayList<>();

    public MainSystem() {}

    public void init(){
        this.initializeCommands();
        Command command = InputParser.parseUserInputToCommand(this).getCommand();
        this.systemState = true;
        while(command != null){
            this.executeCommand(command);
            if (!this.systemState){
                return;
            }
            command = InputParser.parseUserInputToCommand(this).getCommand();
        }
    }

    private void executeCommand(Command command){
        if (command.execute()){
            commandHistory.addCommand(command);
        }
    }

    public void setSystemState(boolean value){
        this.systemState = value;
    }

    private void initializeCommands(){
        this.allCommands = Arrays.asList(new NewCommand(this, CommandNames.NEW.toString()),
                new QuitCommand(this, CommandNames.QUIT.toString()));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
