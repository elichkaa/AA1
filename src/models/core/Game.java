package models.core;

import models.core.tiles.*;
import ui.Command;
import util.MessagePrinter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game implements IGame {
    private final ArrayList<Player> players;
    private final int goldToWin;
    private final Random seed;
    private final LinkedList<Cultivatable> remainingTiles;
    private final Market market;
    private Player currentPlayer;
    private int growVegetablesThisRound;
    private static final String NULL_COMMAND = "Error: No such command exists.";

    public Game(List<Player> players, Random seed, int goldToWin) {
        this.players = (ArrayList<Player>) players;
        this.seed = seed;
        this.remainingTiles = this.initializeRemainingTiles();
        this.shuffleRemainingTiles();
        this.market = new Market();
        this.goldToWin = goldToWin;
    }

    public void processInput(Command command, Player player) {
        try {
            this.currentPlayer = player;
            command.execute(this);
        } catch (NullPointerException nullPointerException) {
            System.out.println(NULL_COMMAND);
        }
    }

    public boolean hasNoWinner() {
        return this.players.stream().filter(Player::isWinner).toList().isEmpty();
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    private void shuffleRemainingTiles() {
        if (this.players != null) {
            Collections.shuffle(this.remainingTiles, seed);
        }
    }

    public void organizeMarket(Player player) {
        this.market.sortMarket(player.getGameBoard().getBarn(),
                player.getGameBoard().getBarn().getSoldVegetablesAfterRound());
    }

    public void setWinnerIfAvailable() {
        for (Player player : this.players) {
            if (player.getGold() >= goldToWin) {
                player.winGame();
            }
        }
    }

    public void setWinnersByForce() {
        if (this.hasNoWinner()) {
            players.sort(Comparator.comparingInt(Player::getGold).reversed());
            int maxGold = players.get(0).getGold();
            for (Player player : this.players) {
                if (player.getGold() >= maxGold) {
                    player.winGame();
                }
            }
        }
    }

    public void decreaseBarnCountdown() {
        this.currentPlayer.getGameBoard().getBarn().updateCountdownIfAvailable();
    }

    public void growVegetables() {
        this.growVegetablesThisRound = this.currentPlayer.getGameBoard().getGrownVegetablesThisRound();
    }

    public void getEndgame() {
        MessagePrinter.printEndgameTable(this.players.stream().map(Player::getPlayerName).toList(),
                this.players.stream().map(Player::getGold).toList(),
                this.players.stream().filter(Player::isWinner).map(Player::getPlayerName).toList());
    }

    public void startTurn(Player player) {
        MessagePrinter.startTurnText(player.getPlayerName(),
                this.growVegetablesThisRound,
                player.getGameBoard().getBarn().areVegetablesSpoiled());
    }

    private LinkedList<Cultivatable> initializeRemainingTiles() {
        if (players == null) {
            return null;
        }
        LinkedList<Cultivatable> tiles = new LinkedList<>();
        for (int i = 0; i < 2 * players.size(); i++) {
            tiles.add(new Garden(null));
        }
        for (int i = 0; i < 3 * players.size(); i++) {
            tiles.add(new Field(null));
        }
        for (int i = 0; i < 2 * players.size(); i++) {
            tiles.add(new LargeField(null));
        }
        for (int i = 0; i < 2 * players.size(); i++) {
            tiles.add(new Forest(null));
        }
        for (int i = 0; i < players.size(); i++) {
            tiles.add(new LargeForest(null));
        }
        return tiles;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Market getMarket() {
        return this.market;
    }

    @Override
    public Cultivatable getFirstRemainingTile() {
        if (remainingTiles.isEmpty()) {
            return null;
        }
        Cultivatable tile = this.remainingTiles.getFirst();
        this.remainingTiles.remove(tile);
        return tile;
    }
}
