package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.ArrayList;
import java.util.List;

public class Barn extends Tile {
    // infinity
    private final static int storageCapacity = Integer.MAX_VALUE;
    private final static int xCoordinate = 0;
    private final static int yCoordinate = 0;
    private final List<Vegetable> storedVegetables;
    private int countdown = 0;

    public Barn() {
        super(new Coordinates(xCoordinate, yCoordinate));
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>();
        this.storedVegetables = new ArrayList<>();
    }

    public void increaseCountdown() {
        this.countdown++;
    }

    public void addVegetable(Vegetable vegetable) {
        this.storedVegetables.add(vegetable);
    }

    public boolean areVegetablesSpoiled() {
        return false;
    }
}
