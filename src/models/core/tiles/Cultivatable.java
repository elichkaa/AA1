package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.*;

public abstract class Cultivatable extends Tile {
    private Vegetable plantedVegetable;

    public Cultivatable(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Cultivatable() {
        super();
    }

    public Vegetable getPlanted() {
        return this.plantedVegetable;
    }

    public void plant(Vegetable vegetable) {
        this.plantedVegetable = vegetable;
    }

    public int getRemainingCapacityToPlant() {
        // TODO: return after countdown and growth implementation
        return 0;
    }

    private String getCapacityRepresentation() {
        return String.format("%d/%d", this.getRemainingCapacityToPlant(), this.capacity);
    }

    private String getCountdownRepresentation() {
        if (this.countdown == 0) {
            return "*";
        }
        return String.valueOf(this.countdown);
    }

    private String getAbbreviationRepresentation() {
        if (this.abbreviation.length() == 2) {
            return " " + this.abbreviation;
        }
        return this.abbreviation + " ";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.appendRow(stringBuilder, this.getAbbreviationRepresentation() + this.getCountdownRepresentation());
        this.appendRow(stringBuilder, String.format("  %s  ", this.getPlanted().getShortName()));
        this.appendRow(stringBuilder, String.format(" %s ", this.getCapacityRepresentation()));
        return stringBuilder.toString();
    }

    private void appendRow(StringBuilder stringBuilder, String text) {
        stringBuilder.append("|").append(text).append("|").append(System.lineSeparator());
    }
}
