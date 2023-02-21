package models.core;

import models.core.tiles.*;

import java.sql.Array;
import java.util.*;

public class GameBoard {
    private final TreeMap<Coordinates, Tile> board;
    private final Tile[][] tileMatrixForPrinting;
    private final Barn barn;
    private final Garden leftGarden;
    private final Garden rightGarden;
    private final Field field;
    private List<Vegetable> soldVegetablesThisRound;
    private Object x;

    public GameBoard() {
        this.barn = new Barn();
        System.out.println(this.barn);
        this.leftGarden = new Garden(new Coordinates(-1, 0));
        this.rightGarden = new Garden(new Coordinates(1, 0));
        this.field = new Field(new Coordinates(0, 1));
        this.board = new TreeMap<>();
        board.put(this.barn.getCoordinates(), this.barn);
        board.put(this.leftGarden.getCoordinates(), this.leftGarden);
        board.put(this.rightGarden.getCoordinates(), this.rightGarden);
        board.put(this.field.getCoordinates(), this.field);
        this.tileMatrixForPrinting = this.getTileMatrix();
    }

    public List<Vegetable> getGrownVegetables() {
        return null;
    }

    public List<Vegetable> getSoldVegetablesThisRound() {
        return null;
    }

    public Barn getBarn() {
        return this.barn;
    }

    private Tile[][] getTileMatrix() {
        int maxWidth = this.board.lastKey().x() - this.board.firstKey().x() + 1;
        List<Integer> yCoordinatesSorted = this.board.keySet().stream().map(Coordinates::y).sorted().toList();
        int maxHeight = yCoordinatesSorted.get(yCoordinatesSorted.size() - 1) - yCoordinatesSorted.get(0) + 1;
        return new Tile[maxHeight][maxWidth];
    }

    @Override
    public String toString() {
        // TODO: print tile matrix
        return "";
    }
}
