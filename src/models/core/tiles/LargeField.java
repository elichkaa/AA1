package models.core.tiles;

import models.core.Coordinates;

public class LargeField extends Field {
    private final static int storageCapacity = 8;
    public LargeField() {
        super();
    }

    public LargeField(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.capacity = storageCapacity;
    }
}
