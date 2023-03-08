package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.List;

// TODO: interface?
public abstract class Tile {
    protected Coordinates coordinates;
    protected int countdown;
    protected int capacity;
    protected String abbreviation = "";
    protected List<Vegetable> allowedVegetables;

    protected Tile(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public boolean canPlant(Vegetable vegetable) {
        return this.allowedVegetables.contains(vegetable);
    }

    protected String getRow(String text) {
        return "|" + text + "|";
    }

    public abstract List<String> getStringRepresentationAsList();

    @Override
    public String toString() {
        List<String> formattedRows = this.getStringRepresentationAsList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formattedRows.get(0)).append(System.lineSeparator());
        stringBuilder.append(formattedRows.get(1)).append(System.lineSeparator());
        stringBuilder.append(formattedRows.get(2));
        return stringBuilder.toString();
    }
}
