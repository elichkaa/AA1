package models.core.tiles;

import models.core.Coordinates;

public class Field extends Tile {
    private final static int storageCapacity = 4;
    public Field() {
        super();
    }

    public Field(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
    }
}