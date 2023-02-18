package models.core;

public enum Vegetable {
    CARROT(1),
    SALAD(2),
    TOMATO(3),
    MUSHROOM(4);
    private final int roundsToGrow;

    // private enum constructor
    Vegetable(int roundsToGrow) {
        this.roundsToGrow = roundsToGrow;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public String getShortName() {
        return this.name().toLowerCase().substring(0, 1);
    }

    public int getRoundsToGrow() {
        return this.roundsToGrow;
    }
}
