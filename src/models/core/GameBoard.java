package models.core;

import models.core.tiles.*;
import java.util.*;

public class GameBoard {
    private final TreeMap<Coordinates, Tile> board;
    private final String[][] tileMatrixForPrinting;
    private final Barn barn;
    private final Garden leftGarden;
    private final Garden rightGarden;
    private final Field field;
    private List<Vegetable> soldVegetablesThisRound;

    public GameBoard() {
        this.barn = new Barn();
        this.leftGarden = new Garden(new Coordinates(-1, 0));
        this.rightGarden = new Garden(new Coordinates(1, 0));
        this.field = new Field(new Coordinates(0, 1));
        this.board = new TreeMap<>();
        board.put(this.barn.getCoordinates(), this.barn);
        board.put(this.leftGarden.getCoordinates(), this.leftGarden);
        board.put(this.rightGarden.getCoordinates(), this.rightGarden);
        board.put(this.field.getCoordinates(), this.field);
        this.tileMatrixForPrinting = this.getTileMatrix();
    }

    public List<Vegetable> getGrownVegetables() {
        return null;
    }

    public List<Vegetable> getSoldVegetablesThisRound() {
        return null;
    }

    public Barn getBarn() {
        return this.barn;
    }

    private String[][] getTileMatrix() {
        List<Integer> xCoordinatesSorted = this.board.keySet().stream().map(Coordinates::x).sorted().toList();
        int maxWidth = xCoordinatesSorted.get(xCoordinatesSorted.size() - 1) - xCoordinatesSorted.get(0);
        List<Integer> yCoordinatesSorted = this.board.keySet().stream().map(Coordinates::y).sorted().toList();
        int maxHeight = yCoordinatesSorted.get(yCoordinatesSorted.size() - 1) - yCoordinatesSorted.get(0);
        String[][] matrix = new String[maxHeight + 1][maxWidth + 1];
        int xCoordinateOfCentre = -xCoordinatesSorted.get(0);
        int yCoordinateOfCentre = yCoordinatesSorted.get(yCoordinatesSorted.size() - 1);
        boolean lastWasEmpty = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Tile tile = this.board.getOrDefault(
                        new Coordinates(j - xCoordinateOfCentre, yCoordinateOfCentre - i), null);
                if (tile == null) {
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
}
