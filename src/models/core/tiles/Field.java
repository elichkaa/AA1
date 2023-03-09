package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.ArrayList;

public class Field extends Cultivatable {
    private final static int storageCapacity = 4;

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
        this.abbreviation = "Fi";
        this.name = "Field";
    }
}