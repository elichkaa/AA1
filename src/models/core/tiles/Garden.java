package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.ArrayList;

public class Garden extends Cultivatable {
    private final static int storageCapacity = 2;

    public Garden(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>() {
            {
                add(Vegetable.CARROT);
                add(Vegetable.MUSHROOM);
                add(Vegetable.SALAD);
                add(Vegetable.TOMATO);
            }
        };
        this.abbreviation = "G";
        this.name = "Garden";
    }
}
