package models.core.tiles;

import models.core.Coordinates;

public class Market extends Tile {
    private final static int storageCapacity = -1;

    public Market() {
        super();
    }

    public Market(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
    }
}
