package models.core;

public record Coordinates(int x, int y) implements Comparable<Coordinates> {
    // returns 0 if both are equal, -1 if not equal
    @Override
    public int compareTo(Coordinates coordinates) {
        int xDifference = this.x() - coordinates.x();
        int yDifference = this.y() - coordinates.y();

        return Integer.compare(xDifference, yDifference);
    }
}
