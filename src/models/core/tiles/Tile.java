package models.core.tiles;

import models.core.Coordinates;

import java.util.List;

// TODO: interface?
public abstract class Tile {
    protected Coordinates coordinates;
    protected int capacity;
    protected String abbreviation = "";
    protected String name = "";

    protected Tile(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return this.name;
    }

    protected String getRow(String text) {
        return "|" + text + "|";
    }

    public abstract List<String> getStringRepresentationAsList();

    @Override
    public String toString() {
        List<String> formattedRows = this.getStringRepresentationAsList();
        return formattedRows.get(0) + System.lineSeparator() +
                formattedRows.get(1) + System.lineSeparator() +
                formattedRows.get(2);
    }
}
