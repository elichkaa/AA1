package models;

import models.core.Game;
import models.core.Player;
import ui.Command;
import ui.SeedParser;
import ui.commands.*;
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
    private static final String QUIT_COMMAND = "quit";
    private static final String BUY_COMMAND = "buy";
    private static final String HARVEST_COMMAND = "harvest";
    private static final String END_TURN_COMMAND = "end turn";
    private static final String SELL_COMMAND = "sell";
    private static final String PLANT_COMMAND = "plant";

    private static final String NULL_COMMAND = "Error: No such command exists.";
    private boolean sessionIsActive = true;
    private boolean turnIsActive = true;
    private int turnActionsCounter = 1;
    private List<Command> allCommands = new ArrayList<>();

    public Session() {
    }

    public void init() {
        IOHandler.printPixelArt();
        this.initializeCommands();
        Scanner scanner = new Scanner(System.in);
        Game game = this.initializeGame(scanner);
        if (!sessionIsActive) {
            scanner.close();
            return;
        }

        CommandParser commandParser = new CommandParser(this, scanner);
        Command command;
        while (game.noWinnerAvailable()) {
            for (Player player : game.getPlayers()) {
                IOHandler.startTurnText(player);
                while (this.turnIsActive && this.sessionIsActive
                        && this.turnActionsCounter <= 2 && game.noWinnerAvailable()) {
                    command = commandParser.parse();
                    while (!isCommandAvailable(command)) {
                        command = commandParser.parse();
                    }
                    command.addStateObserver(x -> this.sessionIsActive = false);
                    command.addTurnObserver(x -> this.turnIsActive = false);
                    if (!game.processInput(command, player)) {
                        continue;
                    }
                    this.turnActionsCounter++;
                }
                if (!this.sessionIsActive) {
                    break;
                }
                this.turnIsActive = true;
                turnActionsCounter = 1;
                game.organizeMarket();
            }
            game.setWinnerIfAvailable();
            // barn countdown
            // vegetable grows
        }
        game.getEndgame();

        scanner.close();
    }

    private Game initializeGame(Scanner scanner) {
        return new Game(this.initializePlayers(scanner), this.getSeed(scanner));
    }

    private List<Player> initializePlayers(Scanner scanner) {
        PlayerParser playerParser = new PlayerParser(scanner);
        return playerParser.parse();
    }

    private Random getSeed(Scanner scanner) {
        if (!sessionIsActive) {
            return null;
        }
        SeedParser seedParser = new SeedParser(scanner);
        return seedParser.parse();
    }

    private void initializeCommands() {
        this.allCommands = Arrays.asList(
                new ShowCommand(SHOW_COMMAND),
                new QuitCommand(QUIT_COMMAND),
                new BuyCommand(BUY_COMMAND),
                new HarvestCommand(HARVEST_COMMAND),
                new SellCommand(SELL_COMMAND),
                new EndTurnCommand(END_TURN_COMMAND),
                new PlantCommand(PLANT_COMMAND));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }

    public boolean isCommandAvailable(Command command) {
        if (command == null) {
            System.out.println(NULL_COMMAND);
            return false;
        }
        return true;
    }
}
