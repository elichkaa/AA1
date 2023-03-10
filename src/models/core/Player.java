package models.core;

import models.core.tiles.Cultivatable;
import util.ErrorPrinter;
import util.MessagePrinter;

import java.util.HashMap;
import java.util.List;

public class Player {
    // TODO: create player interface and refactor QueensFarming class
    private final String playerName;
    private int gold;
    private boolean isWinner;
    GameBoard gameBoard;

    public Player(String playerName, int gold) {
        this.playerName = playerName;
        this.gold = gold;
        this.gameBoard = new GameBoard();
        this.isWinner = false;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    public int getGold() {
        return this.gold;
    }

    public void winGame() {
        this.isWinner = true;
    }

    public boolean isWinner() {
        return this.isWinner;
    }

    public boolean sellAllVegetables(Market market) {
        HashMap<Vegetable, Integer> storedVegetables = this.gameBoard.getBarn().getStoredVegetables();
        int initialGold = this.gold;
        for (Vegetable vegetable : storedVegetables.keySet()) {
            this.gold += market.getVegetablePrice(vegetable);
        }
        int initialSize = storedVegetables.size();
        storedVegetables.clear();
        MessagePrinter.printMessageAfterSell(initialSize, this.gold - initialGold);
        return true;
    }

    public boolean sellSpecificVegetables(Market market, List<String> vegetablesToSell) {
        int initialGold = this.gold;
        HashMap<Vegetable, Integer> storedVegetables = this.gameBoard.getBarn().getStoredVegetables();
        for (String vegetable : vegetablesToSell) {
            Vegetable vegetableObject = Vegetable.valueOf(vegetable.toUpperCase());
            if (storedVegetables.containsKey(vegetableObject)) {
                this.gold += market.getVegetablePrice(vegetableObject);
                storedVegetables.remove(vegetableObject);
            } else {
                ErrorPrinter.print("Barn does not contain a %s.", vegetable);
                return false;
            }
        }
        MessagePrinter.printMessageAfterSell(vegetablesToSell.size(), this.gold - initialGold);
        return true;
    }

    public boolean buyVegetable(String vegetable, Market market) {
        Vegetable vegetableObject = Vegetable.valueOf(vegetable.toUpperCase());
        int vegetablePrice = market.getVegetablePrice(vegetableObject);
        if (vegetablePrice > this.gold) {
            ErrorPrinter.print("Player cannot afford the %s", vegetable);
            return false;
        }
        this.gameBoard.getBarn().addVegetable(vegetableObject);
        this.gold -= vegetablePrice;
        return true;
    }

    public boolean plantVegetable(String vegetableName, int xCoordinate, int yCoordinate) {
        Cultivatable tile = this.getGameBoard().getBoard().getOrDefault(new Coordinates(xCoordinate, yCoordinate),
                null);
        if (tile == null) {
            ErrorPrinter.print("A tile with these coordinates does not exist.");
            return false;
        }
        return tile.plant(Vegetable.valueOf(vegetableName.toUpperCase()));
    }

    public boolean buyLand(int xCoordinate, int yCoordinate, Cultivatable tile) {
        if (tile == null) {
            ErrorPrinter.print("No remaining tiles left. Unable to buy a new one.");
            return false;
        }
        int tilePrice = this.gameBoard.calculateTilePrice(xCoordinate, yCoordinate);
        if (this.gold < tilePrice) {
            ErrorPrinter.print("Player %s does not have enough money to buy the tile.", this.playerName);
            return false;
        }
        Cultivatable newTile = this.gameBoard.addTile(xCoordinate, yCoordinate, tile);
        if (newTile != null) {
            this.gold -= tilePrice;
            MessagePrinter.printMessageAfterBuyingTile(tile.getName(), tilePrice);
            return true;
        } else {
            ErrorPrinter.print("There are no adjacent tiles to these coordinates or coordinates were already taken.");
            return false;
        }
    }

    public boolean harvestVegetable(int xCoordinate, int yCoordinate, int count) {
        Cultivatable tile = this.gameBoard.getBoard().get(new Coordinates(xCoordinate, yCoordinate));

        if (tile == null || tile.getPlanted() == null) {
            ErrorPrinter.print("Tile doesn't exist or nothing is planted on it.");
            return false;
        }
        if (count < 0) {
            ErrorPrinter.print("You cannot harvest a negative amount of vegetables.");
            return false;
        }

        Vegetable plantedOnTile = tile.getPlanted();
        if (tile.harvestVegetable(count)) {
            this.putHarvestedVegetablesInBarn(plantedOnTile, count);
        }

        if (count == 1) {
            MessagePrinter.printOutput("You have harvested " + count + " " + plantedOnTile.getName() + ".");
        } else {
            MessagePrinter.printOutput("You have harvested " + count + " " + plantedOnTile.getPlural() + ".");
        }

        return true;
    }

    private void putHarvestedVegetablesInBarn(Vegetable vegetable, int count) {
        for (int i = 0; i < count; i++) {
            this.gameBoard.getBarn().addVegetable(vegetable);
        }
    }
}
