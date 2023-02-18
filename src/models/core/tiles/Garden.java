package models.core.tiles;

import models.core.Coordinates;

public class Garden extends Tile {
    private final static int storageCapacity = 2;
    public Garden() {
        super();
    }

    public Garden(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
    }
}
