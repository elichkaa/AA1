package models;

import models.core.Game;
import models.core.Player;
import ui.Command;
import ui.commands.NewCommand;
import ui.commands.QuitCommand;
import util.CommandName;
import ui.CommandParser;
import models.parsing.PlayerParser;
import util.IOHandler;
import util.StateObserver;

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

        CommandParser commandParser = new CommandParser();
        commandParser.addObserver(x -> {
            this.sessionState = false;
        });
        Command command = commandParser.parse(this, scanner);
        while(this.sessionState){
            this.executeCommand(command);
            if (!game.hasOutput()) {
                command = commandParser.parse(this, scanner);
                game.processInput(command);
            }
        }
        scanner.close();
    }

    private Game initializeGame(Scanner scanner){
        PlayerParser playerParser = new PlayerParser();
        playerParser.addObserver(x -> {
            this.sessionState = false;
        });
        List<Player> players =  playerParser.parseAll(this, scanner);
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
        this.allCommands = Arrays.asList(new NewCommand(CommandName.NEW.toString()),
                new QuitCommand(CommandName.QUIT.toString()));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
