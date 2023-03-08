package models.core;

public enum Vegetable {
    CARROT(1),
    MUSHROOM(4),
    SALAD(2),
    TOMATO(3);
    private final int roundsToGrow;

    // private enum constructor
    Vegetable(int roundsToGrow) {
        this.roundsToGrow = roundsToGrow;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public String getPlural() {
        if (this == Vegetable.TOMATO) {
            return this.name().toLowerCase() + "es";
        }
        return this.name().toLowerCase() + "s";
    }

    public String getShortName() {
        return this.name().toLowerCase().substring(0, 1);
    }

    public int getRoundsToGrow() {
        return this.roundsToGrow;
    }
}
