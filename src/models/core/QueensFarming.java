package models.core;

import models.core.tiles.*;
import ui.Command;
import util.IOHandler;

import java.util.*;

public class QueensFarming implements IGame {
    private final ArrayList<Player> players;
    private final Random seed;
    private final ArrayList<Tile> remainingTiles;
    private final Market market;
    private static final String NULL_COMMAND = "Error: No such command exists.";

    public QueensFarming(List<Player> players, Random seed) {
        this.players = (ArrayList<Player>) players;
        this.seed = seed;
        this.remainingTiles = this.initializeRemainingTiles();
        this.shuffleRemainingTiles();
        this.market = new Market();
    }

    @Override
    public boolean processInput(Command command, Player player) {
        try {
            return command.execute();
        } catch (NullPointerException nullPointerException) {
            System.out.println(NULL_COMMAND);
            return false;
        }
    }

    @Override
    public boolean hasOutput() {
        return false;
    }

    @Override
    public String getOutput() {
        return "";
    }

    @Override
    public void setOutput() {

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
        this.market.sortMarket(player.getGameBoard().getBarn(), player.getGameBoard().getSoldVegetablesThisRound());
    }

    public void setWinnerIfAvailable() {
        for (Player player : this.players) {
            if (player.getStartingGold() >= player.getGoldToWin()) {
                player.winGame();
            }
        }
    }

    public void getEndgame() {
        IOHandler.printEndgameTable(this.players);
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
}
