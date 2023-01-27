package models;

import ui.Command;
import ui.CommandHistory;
import ui.NewCommand;
import ui.QuitCommand;
import ui.CommandNames;
import util.InputParser;
import java.util.ArrayList;
import java.util.List;

public class MainSystem {
    private final CommandHistory commandHistory = new CommandHistory();
    private boolean systemState;
    private List<Command> allCommands;

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
        MainSystem system = this;
        this.allCommands = new ArrayList<>() {{
            add(new NewCommand(system, CommandNames.NEW.toString()));
            add(new QuitCommand(system, CommandNames.QUIT.toString()));
        }};
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
