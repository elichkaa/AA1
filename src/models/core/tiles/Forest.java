package models.core.tiles;

import models.core.Coordinates;

public class Forest extends Tile {
    private final static int storageCapacity = 4;
    public Forest() {
        super();
    }

    public Forest(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
    }
}
