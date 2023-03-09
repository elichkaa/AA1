package models.core;

import models.core.tiles.*;
import util.ErrorPrinter;

import java.util.*;

public class GameBoard {
    private final TreeMap<Coordinates, Cultivatable> board;
    private String[][] tileMatrixForPrinting;
    private final Barn barn;
    private int xCoordinateOfBarn;
    private int yCoordinateOfBarn;

    public GameBoard() {
        this.barn = new Barn();
        Garden leftGarden = new Garden(new Coordinates(-1, 0));
        Garden rightGarden = new Garden(new Coordinates(1, 0));
        Field field = new Field(new Coordinates(0, 1));
        this.board = new TreeMap<>() {{
            put(leftGarden.getCoordinates(), leftGarden);
            put(rightGarden.getCoordinates(), rightGarden);
            put(field.getCoordinates(), field);
        }};
        this.tileMatrixForPrinting = this.getTileMatrix();
    }

    public int getGrownVegetablesCount() {
        return 0;
    }

    public Barn getBarn() {
        return this.barn;
    }

    public TreeMap<Coordinates, Cultivatable> getBoard() {
        return this.board;
    }

    public void growVegetablesOnBoard() {
        for (Cultivatable tile : board.values()) {
            tile.decreaseVegetableCountdown();
        }
    }

    private String[][] getTileMatrix() {
        int maxWidth = this.board.lastKey().x() - this.board.firstKey().x();
        List<Integer> yCoordinatesSorted = this.board.keySet().stream().map(Coordinates::y).sorted().toList();
        int maxHeight = yCoordinatesSorted.get(yCoordinatesSorted.size() - 1) - yCoordinatesSorted.get(0);

        String[][] matrix = new String[maxHeight + 1][maxWidth + 1];
        int xCoordinateOfCentre = -this.board.firstKey().x();
        int yCoordinateOfCentre = yCoordinatesSorted.get(yCoordinatesSorted.size() - 1);

        boolean lastWasEmpty = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Cultivatable tile = this.board.getOrDefault(
                        new Coordinates(j - xCoordinateOfCentre, yCoordinateOfCentre - i), null);
                if (j - xCoordinateOfCentre == 0 && yCoordinateOfCentre - i == 0) {
                    matrix[i][j] = this.barn.toString();
                    lastWasEmpty = false;
                } else if (tile == null) {
                    matrix[i][j] = this.createEmptyMatrix(j, maxWidth, lastWasEmpty);
                    lastWasEmpty = true;
                } else {
                    matrix[i][j] = tile.toString();
                    lastWasEmpty = false;
                }
            }
            lastWasEmpty = false;
        }
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder gameBoardBuilder = new StringBuilder();
        this.tileMatrixForPrinting = getTileMatrix();
        for (String[] tiles : this.tileMatrixForPrinting) {
            StringBuilder firstRowBuilder = new StringBuilder();
            StringBuilder secondRowBuilder = new StringBuilder();
            StringBuilder thirdRowBuilder = new StringBuilder();
            for (String tile : tiles) {
                firstRowBuilder.append(Arrays.stream(tile.split("\r\n")).toList().get(0));
                secondRowBuilder.append(Arrays.stream(tile.split("\r\n")).toList().get(1));
                thirdRowBuilder.append(Arrays.stream(tile.split("\r\n")).toList().get(2));
            }
            firstRowBuilder.append(System.lineSeparator());
            secondRowBuilder.append(System.lineSeparator());
            thirdRowBuilder.append(System.lineSeparator());
            gameBoardBuilder.append(firstRowBuilder.toString().replaceAll("\\|\\|", "|"))
                    .append(secondRowBuilder.toString().replaceAll("\\|\\|", "|"))
                    .append(thirdRowBuilder.toString().replaceAll("\\|\\|", "|"));
        }

        // TODO: get rid of last line separator
        return gameBoardBuilder.toString();
    }

    private String createEmptyMatrix(int currentColumn, int maxWidth, boolean lastWasEmpty) {
        char[] emptyRow;
        if (lastWasEmpty) {
            if (currentColumn < maxWidth && currentColumn > 0) {
                emptyRow = new char[6];
            } else {
                emptyRow = new char[7];
            }
        } else {
            if (currentColumn < maxWidth && currentColumn > 0) {
                emptyRow = new char[5];
            } else {
                emptyRow = new char[6];
            }
        }
        Arrays.fill(emptyRow, ' ');

        return (String.valueOf(emptyRow) + System.lineSeparator() +
                String.valueOf(emptyRow) + System.lineSeparator() + String.valueOf(emptyRow));
    }

    public Cultivatable addTile(int xCoordinate, int yCoordinate, Cultivatable tile) {
        if (this.tileIsNotContained(xCoordinate, yCoordinate)
                && isAdjacentToOtherTiles(xCoordinate, yCoordinate)) {
            this.board.put(new Coordinates(xCoordinate, yCoordinate), tile);
            return tile;
        } else {
            return null;
        }
    }

    // manhattan distance
    public int calculateTilePrice(int xCoordinate, int yCoordinate) {
        return 10 * (Math.abs(xCoordinate) + Math.abs(yCoordinate) - 1);
    }

    public boolean isAdjacentToOtherTiles(int xCoordinate, int yCoordinate) {
        return this.board.containsKey(new Coordinates(xCoordinate - 1, yCoordinate))
                || this.board.containsKey(new Coordinates(xCoordinate, yCoordinate - 1))
                || this.board.containsKey(new Coordinates(xCoordinate + 1, yCoordinate));
    }

    public boolean tileIsNotContained(int xCoordinate, int yCoordinate) {
        return !this.board.containsKey(new Coordinates(xCoordinate, yCoordinate))
                && !(xCoordinate == 0 && yCoordinate == 0);
    }
}
