package models.core;

import models.core.tiles.*;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class GameBoard {
    private final TreeMap<Coordinates, Tile> board;
    private final String[][] tileMatrixForPrinting;
    private final Barn barn;
    private final Garden leftGarden;
    private final Garden rightGarden;
    private final Field field;
    private List<Vegetable> soldVegetablesThisRound;
    private Object x;

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
        board.put(new Coordinates(-2, 0), this.field);
        board.put(new Coordinates(-3, 0), this.field);
        board.put(new Coordinates(-3, 1), this.field);
        board.put(new Coordinates(-3, 1), this.field);
        board.put(new Coordinates(-3, 2), this.field);
        board.put(new Coordinates(-3, 3), this.field);
        board.put(new Coordinates(-3, 4), this.field);
        board.put(new Coordinates(-4, 0), this.field);
        this.tileMatrixForPrinting = this.getTileMatrix();
        System.out.println(this);
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
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int x = j - maxWidth + 1;
                int y = maxHeight - i;
                Tile tile = this.board.getOrDefault(new Coordinates(x, y), null);
                if (tile == null) {
                    char[] emptyRow = new char[7];
                    Arrays.fill(emptyRow, ' ');
                    emptyRow[0] = '#';
                    emptyRow[emptyRow.length - 1] = '#';
                    matrix[i][j] = (String.valueOf(emptyRow) + System.lineSeparator() +
                            String.valueOf(emptyRow) + System.lineSeparator() + String.valueOf(emptyRow));
                } else {
                    matrix[i][j] = tile.toString();
                }
            }
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
            gameBoardBuilder.append(firstRowBuilder.toString()
                            .replaceAll("\\|\\|", "|")
                            .replaceAll("##|#", " "))
                    .append(secondRowBuilder.toString()
                            .replaceAll("\\|\\|", "|")
                            .replaceAll("##|#", " "))
                    .append(thirdRowBuilder.toString()
                            .replaceAll("\\|\\|", "|")
                            .replaceAll("##|#", " "));
        }

        return gameBoardBuilder.toString();
    }
}
