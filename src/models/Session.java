package models;

import models.core.Game;
import models.core.Player;
import ui.Command;
import ui.SeedParser;
import ui.commands.BuyCommand;
import ui.commands.HarvestCommand;
import ui.commands.SellCommand;
import ui.commands.ShowCommand;
import ui.commands.PlantCommand;
import util.CommandName;
import ui.CommandParser;
import ui.PlayerParser;
import util.ErrorMessage;
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
        commandParser.addObserver(x -> this.sessionState = false);
        Command command = commandParser.parse();
        while(this.sessionState){
            this.executeCommand(command);
            if (!game.hasOutput()) {
                command = commandParser.parse();
                game.processInput(command);
            }
            command = commandParser.parse();
        }
        scanner.close();
    }

    private Game initializeGame(Scanner scanner) {
        return new Game(this.initializePlayers(scanner), this.getSeed(scanner));
    }

    private List<Player> initializePlayers(Scanner scanner) {
        PlayerParser playerParser = new PlayerParser(scanner);
        playerParser.addObserver(x -> this.sessionState = false);
        return playerParser.parse();
    }

    private int getSeed(Scanner scanner) {
        SeedParser seedParser = new SeedParser(scanner);
        seedParser.addObserver(x -> this.sessionState = false);
        return seedParser.parse();
    }

    private void executeCommand(Command command) {
        try {
            command.execute();
        } catch (NullPointerException nullPointerException) {
            System.out.println(ErrorMessage.NULL_COMMAND);
        }

    }

    private void initializeCommands(){
        this.allCommands = Arrays.asList(new ShowCommand(CommandName.SHOW.toString()),
                new BuyCommand(CommandName.BUY.toString()),
                new HarvestCommand(CommandName.HARVEST.toString()),
                new SellCommand(CommandName.SELL.toString()),
                new PlantCommand(CommandName.PLANT.toString()));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
