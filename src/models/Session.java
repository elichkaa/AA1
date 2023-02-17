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
import ui.CommandParser;
import ui.PlayerParser;
import util.IOHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Session {
    private static final String SHOW_COMMAND = "show";
    private static final String BUY_COMMAND = "buy";
    private static final String HARVEST_COMMAND = "harvest";
    private static final String SELL_COMMAND = "sell";
    private static final String PLANT_COMMAND = "show";
    private static final String NULL_COMMAND = "Error: Command was null and could not be executed.";
    private boolean sessionState = true;
    private List<Command> allCommands = new ArrayList<>();

    public Session() {
    }

    public void init() {
        IOHandler.printPixelArt();
        this.initializeCommands();
        Scanner scanner = new Scanner(System.in);
        Game game = this.initializeGame(scanner);
        if (!sessionState) {
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

    private Random getSeed(Scanner scanner) {
        if (!sessionState) {
            return null;
        }
        SeedParser seedParser = new SeedParser(scanner);
        seedParser.addObserver(x -> this.sessionState = false);
        return seedParser.parse();
    }

    private void executeCommand(Command command) {
        try {
            command.execute();
        } catch (NullPointerException nullPointerException) {
            System.out.println(NULL_COMMAND);
        }

    }

    private void initializeCommands() {
        this.allCommands = Arrays.asList(
                new ShowCommand(SHOW_COMMAND),
                new BuyCommand(BUY_COMMAND),
                new HarvestCommand(HARVEST_COMMAND),
                new SellCommand(SELL_COMMAND),
                new PlantCommand(PLANT_COMMAND));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }
}
