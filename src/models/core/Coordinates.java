package models.core;

public record Coordinates(int x, int y) implements Comparable<Coordinates> {
    @Override
    public int compareTo(Coordinates coordinates) {
        int distance = Integer.compare(this.getDistanceBetweenCoordinates(this, coordinates), 0);
        if (coordinates.y > this.y || coordinates.x > this.x) return -distance;
        return distance;
    }

    private int getDistanceBetweenCoordinates(Coordinates firstCoordinates, Coordinates secondCoordinates) {
        return (int) Math.sqrt(Math.pow(secondCoordinates.x - firstCoordinates.x, 2)
                + Math.pow(secondCoordinates.y - firstCoordinates.y, 2));
    }
}
