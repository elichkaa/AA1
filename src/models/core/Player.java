package models.core;

public class Player {
    // TODO: create player interface and refactor QueensFarming class
    private final String playerName;
    private final int gold;
    private boolean isWinner;
    GameBoard gameBoard;

    public Player(String playerName, int startingGold) {
        this.playerName = playerName;
        this.gold = startingGold;
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
}
