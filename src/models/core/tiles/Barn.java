package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Barn extends Tile {
    // infinity
    private final static int storageCapacity = Integer.MAX_VALUE;
    private final static int xCoordinate = 0;
    private final static int yCoordinate = 0;
    private final List<Vegetable> storedVegetables;

    public Barn() {
        super(new Coordinates(xCoordinate, yCoordinate));
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>();
        this.storedVegetables = new ArrayList<>();
        this.abbreviation = "B";
    }

    public void increaseCountdown() {
        this.countdown++;
    }

    public void addVegetable(Vegetable vegetable) {
        this.storedVegetables.add(vegetable);
    }

    public boolean areVegetablesSpoiled() {
        return false;
    }

    public List<Vegetable> getRemainingVegetablesAfterSell(List<Vegetable> soldVegetables) {
        // TODO: possible error if the soldVegetable is not contained in the storedVegetables
        for (Vegetable soldVegetable : soldVegetables) {
            Vegetable firstMatchedVegetable = this.storedVegetables.stream()
                    .filter(x -> x.getName().equals(soldVegetable.getName()))
                    .findFirst()
                    .orElse(null);
            this.storedVegetables.remove(firstMatchedVegetable);
        }
        return this.storedVegetables;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] emptyRow = new char[5];
        Arrays.fill(emptyRow, ' ');
        this.appendRow(stringBuilder, String.valueOf(emptyRow));
        this.appendRow(stringBuilder, String.format(" %s %d ", this.abbreviation, this.countdown));
        this.appendRow(stringBuilder, String.valueOf(emptyRow));
        return stringBuilder.toString();
    }

    private void appendRow(StringBuilder stringBuilder, String text) {
        stringBuilder.append("|").append(text).append("|").append(System.lineSeparator());
    }
}
