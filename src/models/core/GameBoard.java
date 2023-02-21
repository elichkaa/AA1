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
        System.out.println(this.barn);
        this.leftGarden = new Garden(new Coordinates(-1, 0));
        this.rightGarden = new Garden(new Coordinates(1, 0));
        this.field = new Field(new Coordinates(0, 1));
        this.board = new TreeMap<>();
        board.put(this.barn.getCoordinates(), this.barn);
        board.put(this.leftGarden.getCoordinates(), this.leftGarden);
        board.put(this.rightGarden.getCoordinates(), this.rightGarden);
        board.put(this.field.getCoordinates(), this.field);
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
        int maxWidth = this.board.lastKey().x() - this.board.firstKey().x();
        List<Integer> yCoordinatesSorted = this.board.keySet().stream().map(Coordinates::y).sorted().toList();
        int maxHeight = yCoordinatesSorted.get(yCoordinatesSorted.size() - 1) - yCoordinatesSorted.get(0);
        String[][] matrix = new String[maxHeight + 1][maxWidth + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Tile tile = this.board.getOrDefault(new Coordinates(j - maxHeight, maxWidth - i - 1), null);
                if (tile == null) {
                    char[] emptyRow = new char[6];
                    Arrays.fill(emptyRow, ' ');
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
            gameBoardBuilder.append(firstRowBuilder.toString().replaceAll("\\|\\|", "|"))
                    .append(secondRowBuilder.toString().replaceAll("\\|\\|", "|"))
                    .append(thirdRowBuilder.toString().replaceAll("\\|\\|", "|"));
        }

        return gameBoardBuilder.toString();
    }
}
