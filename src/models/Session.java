package models;

import models.core.Game;
import models.core.Player;
import ui.Command;
import ui.commands.ShowCommand;
import ui.commands.QuitCommand;
import util.CommandName;
import ui.CommandParser;
import ui.PlayerParser;
import util.IOHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Session {
    private boolean sessionState = true;
    private List<Command> allCommands = new ArrayList<>();
    public Session() {}

    public void init() {
        IOHandler.printPixelArt();
        this.initializeCommands();
        Scanner scanner = new Scanner(System.in);
        Game game = this.initializeGame(scanner);
        if (!sessionState){
            scanner.close();
            return;
        }

        CommandParser commandParser = new CommandParser(this, scanner);
        commandParser.addObserver(x -> {
            this.sessionState = false;
        });
        Command command = commandParser.parse();
        while(this.sessionState){
            this.executeCommand(command);
            if (!game.hasOutput()) {
                command = commandParser.parse();
                game.processInput(command);
            }
        }
        scanner.close();
    }

    private Game initializeGame(Scanner scanner){
        PlayerParser playerParser = new PlayerParser(scanner);
        playerParser.addObserver(x -> {
            this.sessionState = false;
        });
        List<Player> players =  playerParser.parse();
        return new Game(5, players);
    }

    private void executeCommand(Command command){
        try {
            command.execute();
        } catch (NullPointerException nullPointerException){
            System.out.println("Command was null and could not be executed.");
        }

    }

    private void initializeCommands(){
        this.allCommands = Arrays.asList(new ShowCommand(CommandName.SHOW.toString()),
                new QuitCommand(CommandName.QUIT.toString()));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
