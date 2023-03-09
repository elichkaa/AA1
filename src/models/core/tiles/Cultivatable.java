package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;
import util.ErrorPrinter;

import java.util.Arrays;
import java.util.List;

public abstract class Cultivatable extends Tile {
    private int countdownToGrow;
    private Vegetable plantedVegetable;

    protected List<Vegetable> allowedVegetables;

    public Cultivatable(Coordinates coordinates) {
        super(coordinates);
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

    public void decreaseVegetableCountdown() {
        if (plantedVegetable != null && countdown > 0) {
            this.countdownToGrow--;
        }
    }

    public int getRemainingCapacityToPlant() {
        // TODO: return after countdown and growth implementation
        return 0;
    }

    private String getCapacityRepresentation() {
        return String.format("%d/%d", this.getRemainingCapacityToPlant(), this.capacity);
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
