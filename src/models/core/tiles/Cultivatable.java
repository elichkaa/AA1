package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.Arrays;
import java.util.List;

public abstract class Cultivatable extends Tile {
    private Vegetable plantedVegetable;

    public Cultivatable(Coordinates coordinates) {
        super(coordinates);
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
        String abbreviationText = "";
        switch (abbreviation.length()) {
            case 1 -> abbreviationText += " " + this.abbreviation + " " + this.getCountdownRepresentation() + " ";
            case 2 -> abbreviationText += " " + this.abbreviation + " " + this.getCountdownRepresentation();
            case 3 -> abbreviationText += this.abbreviation + " ";
        }
        return abbreviationText;
    }

    private String getPlantedRepresentation() {
        if (this.getPlanted() == null) {
            return " ";
        }
        return this.plantedVegetable.getShortName();
    }

    public List<String> getStringRepresentationAsList() {
        return Arrays.asList(this.getRow(this.getAbbreviationRepresentation()),
                this.getRow(String.format("  %s  ", this.getPlantedRepresentation())),
                this.getRow(String.format(" %s ", this.getCapacityRepresentation())));
    }
}
