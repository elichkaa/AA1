package models.core;

import models.core.tiles.*;
import ui.Command;
import util.MessagePrinter;

import java.util.*;

public class Game implements IGame {
    private final ArrayList<Player> players;
    private final int goldToWin;
    private final Random seed;
    private final ArrayList<Tile> remainingTiles;
    private final Market market;
    private Player currentPlayer;
    private static final String NULL_COMMAND = "Error: No such command exists.";

    public Game(List<Player> players, Random seed, int goldToWin) {
        this.players = (ArrayList<Player>) players;
        this.seed = seed;
        this.remainingTiles = this.initializeRemainingTiles();
        this.shuffleRemainingTiles();
        this.market = new Market();
        this.goldToWin = goldToWin;
    }

    @Override
    public boolean processInput(Command command, Player player) {
        try {
            this.currentPlayer = player;
            return command.execute(this);
        } catch (NullPointerException nullPointerException) {
            System.out.println(NULL_COMMAND);
            return false;
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

    public void increaseBarnCountdown() {
        this.currentPlayer.getGameBoard().getBarn().updateCountdownIfAvailable();
    }

    public void growVegetables() {
        this.currentPlayer.getGameBoard().growVegetablesOnBoard();
    }

    public void getEndgame() {
        MessagePrinter.printEndgameTable(this.players.stream().map(Player::getPlayerName).toList(),
                this.players.stream().map(Player::getGold).toList(),
                this.players.stream().filter(Player::isWinner).map(Player::getPlayerName).toList());
    }

    public void startTurn(Player player) {
        MessagePrinter.startTurnText(player.getPlayerName(),
                player.getGameBoard().getGrownVegetablesCount(),
                player.getGameBoard().getBarn().areVegetablesSpoiled());
    }

    private ArrayList<Tile> initializeRemainingTiles() {
        if (players == null) {
            return null;
        }
        ArrayList<Tile> tiles = new ArrayList<>();
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
}
