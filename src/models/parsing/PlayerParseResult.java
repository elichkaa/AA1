package models.parsing;

import models.core.Player;

public class PlayerParseResult implements IParseResult<Player>{
    private String playerName;
    private int startingGold;
    private int goldToWin;

    public PlayerParseResult(String playerName, int startingGold, int goldToWin){
        this.playerName = playerName;
        this.startingGold = startingGold;
        this.goldToWin = goldToWin;
    }
    @Override
    public Player getResult() {
        return null;
    }
}
