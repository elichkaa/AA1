package models.parsing;

import models.core.Player;
import ui.Command;

import java.util.List;

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
        return new Player(this.playerName, this.startingGold, this.goldToWin);
    }
}
