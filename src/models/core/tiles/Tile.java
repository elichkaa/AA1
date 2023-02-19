package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.List;

public abstract class Tile {
    protected Coordinates coordinates;
    protected int capacity;
    protected List<Vegetable> allowedVegetables;

    public Tile() {
    }

    public Tile(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean canPlant(Vegetable vegetable) {
        return this.allowedVegetables.contains(vegetable);
    }
}
