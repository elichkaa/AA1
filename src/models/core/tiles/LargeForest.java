package models.core.tiles;

import models.core.Coordinates;

public class LargeForest extends Forest {
    private final static int storageCapacity = 8;
    public LargeForest() {
        super();
    }

    public LargeForest(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
    }
}
