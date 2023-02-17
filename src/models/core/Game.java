package models.core;

import models.core.tiles.*;
import ui.Command;

import java.util.*;

public class Game implements IGame {
    private final ArrayList<Player> players;
    private final Random seed;
    private final ArrayList<Tile> remainingTiles;

    public Game(List<Player> players, Random seed) {
        this.players = (ArrayList<Player>) players;
        this.seed = seed;
        this.remainingTiles = this.initializeRemainingTiles();
        this.shuffleRemainingTiles();
    }

    @Override
    public void processInput(Command command) {

    }

    @Override
    public void requestInput() {

    }

    @Override
    public boolean hasOutput() {
        return false;
    }

    private void shuffleRemainingTiles() {
        if (this.players != null) {
            Collections.shuffle(this.remainingTiles, seed);
        }
    }

    private ArrayList<Tile> initializeRemainingTiles() {
        if (players == null) {
            return null;
        }
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 2 * players.size(); i++) {
            tiles.add(new Garden());
        }
        for (int i = 0; i < 3 * players.size(); i++) {
            tiles.add(new Field());
        }
        for (int i = 0; i < 2 * players.size(); i++) {
            tiles.add(new LargeField());
        }
        for (int i = 0; i < 2 * players.size(); i++) {
            tiles.add(new Forest());
        }
        for (int i = 0; i < players.size(); i++) {
            tiles.add(new LargeForest());
        }
        return tiles;
    }
}
