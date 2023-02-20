package models.core;

public class Player {
    private final String playerName;
    private final int startingGold;
    private final int goldToWin;
    private boolean isWinner;
    GameBoard gameBoard;

    public Player(String playerName, int startingGold, int goldToWin) {
        this.playerName = playerName;
        this.startingGold = startingGold;
        this.goldToWin = goldToWin;
        this.gameBoard = new GameBoard();
        this.isWinner = false;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    public int getStartingGold() {
        return this.startingGold;
    }

    public int getGoldToWin() {
        return this.goldToWin;
    }

    public void winGame() {
        this.isWinner = true;
    }

    public boolean isWinner() {
        return this.isWinner;
    }
}
