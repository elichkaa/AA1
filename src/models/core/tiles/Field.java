package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.ArrayList;

public class Field extends Tile {
    private final static int storageCapacity = 4;
    public Field() {
        super();
    }

    public Field(Coordinates coordinates) {
        super(coordinates);
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>() {
            {
                add(Vegetable.CARROT);
                add(Vegetable.SALAD);
                add(Vegetable.TOMATO);
            }
        };
    }
}