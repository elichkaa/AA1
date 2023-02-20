package models.core;

import models.core.tiles.Barn;
import models.core.tiles.Field;
import models.core.tiles.Garden;
import models.core.tiles.Tile;

import java.util.List;
import java.util.TreeMap;

public class GameBoard {
    private final TreeMap<Coordinates, Tile> board = new TreeMap<>();
    private final Barn barn;
    private final Garden leftGarden;
    private final Garden rightGarden;
    private final Field field;
    private final Market market;

    public GameBoard() {
        this.barn = new Barn();
        this.leftGarden = new Garden(new Coordinates(-1, 0));
        this.rightGarden = new Garden(new Coordinates(1, 0));
        this.field = new Field(new Coordinates(0, 1));
        board.put(this.barn.getCoordinates(), this.barn);
        board.put(this.leftGarden.getCoordinates(), this.leftGarden);
        board.put(this.rightGarden.getCoordinates(), this.rightGarden);
        board.put(this.field.getCoordinates(), this.field);
        market = new Market(this.barn);
    }

    public List<Vegetable> getGrownVegetables() {
        return null;
    }

    public Barn getBarn() {
        return this.barn;
    }

    public Market getMarket() {
        return this.market;
    }
}
