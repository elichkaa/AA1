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
import java.util.Scanner;

public class Session {
    private final CommandHistory commandHistory = new CommandHistory();
    private boolean sessionState = false;
    private List<Command> allCommands = new ArrayList<>();
    public Session() {}

    public void init(){
        this.initializeCommands();
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        Command command = InputParser.parseUserInputAsCommand(this, scanner).getCommand();
        this.sessionState = true;
        while(this.sessionState){
            this.executeCommand(command);
            if (command.getCommandName().equals(CommandNames.QUIT.toString())){
                this.terminateSession();
            }
            if (!game.hasOutput()){
                command = InputParser.parseUserInputAsCommand(this, scanner).getCommand();
                game.processInput(command);
            }
        }
        scanner.close();
    }

    private void executeCommand(Command command){
        try {
            if (command.execute()){
                commandHistory.addCommand(command);
            }
        } catch (NullPointerException nullPointerException){
            System.out.println("Command was null and could not be executed.");
        }

    }

    private void terminateSession(){
        this.sessionState = false;
    }

    private void initializeCommands(){
        this.allCommands = Arrays.asList(new NewCommand(CommandNames.NEW.toString()),
                new QuitCommand(CommandNames.QUIT.toString()));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
