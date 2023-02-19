package models.core.tiles;

import models.core.Coordinates;

import java.util.ArrayList;

public class Barn extends Tile {
    // infinity
    private final static int storageCapacity = Integer.MAX_VALUE;
    private final static int xCoordinate = 0;
    private final static int yCoordinate = 0;

    public Barn() {
        super(new Coordinates(xCoordinate, yCoordinate));
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>();
    }
}
