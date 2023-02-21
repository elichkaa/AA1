package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.List;

public abstract class Tile {
    protected Coordinates coordinates;
    protected int capacity;
    protected String abbreviation = "";
    protected List<Vegetable> allowedVegetables;
    protected int countdown = 0;

    protected Tile(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public boolean canPlant(Vegetable vegetable) {
        return this.allowedVegetables.contains(vegetable);
    }

    protected char getCountdownCharRepresentation() {
        if (this.countdown == 0) {
            return '*';
        } else {
            return (char) this.countdown;
        }
    }

    @Override
    public abstract String toString();
}
