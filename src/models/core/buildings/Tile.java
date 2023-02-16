package models.core.buildings;

import models.core.Coordinates;

public abstract class Tile {
    protected Coordinates coordinates;

    public Tile() {

    }

    public Tile(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
