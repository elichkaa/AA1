package models.core.tiles;

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
