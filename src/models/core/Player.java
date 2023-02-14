package models.core;

public class Player {
    private String playerName;
    private int startingGold;
    private int goldToWin;

    public Player(String playerName, int startingGold, int goldToWin){

        this.playerName = playerName;
        this.startingGold = startingGold;
        this.goldToWin = goldToWin;
    }
}
