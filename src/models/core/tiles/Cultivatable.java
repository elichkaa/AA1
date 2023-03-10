package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;
import util.ErrorPrinter;

import java.util.Arrays;
import java.util.List;

public abstract class Cultivatable extends Tile {
    private int countdownToGrow;
    private Vegetable plantedVegetable;
    private int plantedVegetableCount;
    protected List<Vegetable> allowedVegetables;

    public Cultivatable(Coordinates coordinates) {
        super(coordinates);
        this.plantedVegetableCount = 0;
    }

    public Vegetable getPlanted() {
        return this.plantedVegetable;
    }

    public boolean plant(Vegetable vegetable) {
        if (this.plantedVegetable != null) {
            ErrorPrinter.print("%s is already planted on this tile.", this.plantedVegetable.getName());
            return false;
        }

        if (this.canPlant(vegetable)) {
            this.plantedVegetable = vegetable;
            this.plantedVegetableCount = 1;
            this.countdownToGrow = vegetable.getRoundsToGrow();
            return true;
        } else {
            ErrorPrinter.print("%s is not allowed on this tile.", vegetable.getName());
        }
        return false;
    }

    private boolean canPlant(Vegetable vegetable) {
        return this.allowedVegetables.contains(vegetable);
    }

    public boolean growVegetable() {
        if (plantedVegetable != null) {
            if (countdownToGrow > 0) {
                this.countdownToGrow--;
            }
            if (countdownToGrow == 0) {
                this.plantedVegetableCount *= 2;
                return true;
            }
        }

        return false;
    }

    public boolean harvestVegetable(int count) {
        if (count > this.plantedVegetableCount) {
            ErrorPrinter.print("There are not that many vegetables grown on the tile.");
            return false;
        }
        this.plantedVegetableCount -= count;
        if (this.plantedVegetableCount == 0) {
            countdownToGrow = 0;
        } else if (this.countdownToGrow == 0) {
            this.countdownToGrow = this.plantedVegetable.getRoundsToGrow();
        }
        return true;
    }

    private String getCapacityRepresentation() {
        return String.format("%d/%d", this.plantedVegetableCount, this.capacity);
    }

    private String getCountdownRepresentation() {
        if (this.countdownToGrow == 0) {
            return "*";
        }
        return String.valueOf(this.countdownToGrow);
    }

    private String getAbbreviationRepresentation() {
        String abbreviationText = "";
        switch (abbreviation.length()) {
            case 1 -> abbreviationText += " " + this.abbreviation + " " + this.getCountdownRepresentation() + " ";
            case 2 -> abbreviationText += " " + this.abbreviation + " " + this.getCountdownRepresentation();
            case 3 -> abbreviationText += this.abbreviation + " " + this.getCountdownRepresentation();
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
