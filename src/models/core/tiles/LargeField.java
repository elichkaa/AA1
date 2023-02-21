package models.core.tiles;

import models.core.Coordinates;

public class LargeField extends Cultivatable {
    private final static int storageCapacity = 8;

    public LargeField(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
        this.abbreviation = "LFi";
    }
}
