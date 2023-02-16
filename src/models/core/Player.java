package models.core;

public class Player {
    private final String playerName;
    private final int startingGold;
    private final int goldToWin;
    GameBoard gameBoard;

    public Player(String playerName, int startingGold, int goldToWin) {
        this.playerName = playerName;
        this.startingGold = startingGold;
        this.goldToWin = goldToWin;
        this.gameBoard = new GameBoard();
    }

    public String getPlayerName() {
        return this.playerName;
    }
}
