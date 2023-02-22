package models.core;

public record Coordinates(int x, int y) implements Comparable<Coordinates> {
    @Override
    public int compareTo(Coordinates coordinates) {
        // returning positive distance means coordinates are bigger or the same
        // returning negative distance means coordinates are smaller
        int distance = Integer.compare(this.getDistanceBetweenCoordinates(this, coordinates), 0);
        if (this.x < coordinates.x) {
            return -distance;
        } else if (this.x == coordinates.x) {
            if (this.y < coordinates.y) {
                return -distance;
            } else {
                return distance;
            }
        } else {
            return distance;
        }
    }

    private int getDistanceBetweenCoordinates(Coordinates firstCoordinates, Coordinates secondCoordinates) {
        return (int) Math.sqrt(Math.pow(secondCoordinates.x - firstCoordinates.x, 2)
                + Math.pow(secondCoordinates.y - firstCoordinates.y, 2));
    }
}
