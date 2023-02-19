package models.core;

import models.core.tiles.Barn;
import models.core.tiles.Field;
import models.core.tiles.Garden;
import models.core.tiles.Tile;

import java.util.TreeMap;

public class GameBoard {
    private TreeMap<Tile, Tile> board = new TreeMap<>();
    private final Barn barn;
    private final Garden leftGarden;
    private final Garden rightGarden;
    private final Field field;

    public GameBoard() {
        this.barn = new Barn();
        this.leftGarden = new Garden(new Coordinates(-1, 0));
        this.rightGarden = new Garden(new Coordinates(1, 0));
        this.field = new Field(new Coordinates(0, 1));
        this.board.put(barn, leftGarden);
        this.board.put(barn, rightGarden);
    }
}
