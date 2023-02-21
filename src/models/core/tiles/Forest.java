package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.ArrayList;

public class Forest extends Cultivatable {
    private final static int storageCapacity = 4;

    public Forest(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>() {
            {
                add(Vegetable.CARROT);
                add(Vegetable.MUSHROOM);
            }
        };
        this.abbreviation = "Fo";
    }
}
