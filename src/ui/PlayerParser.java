package ui;

import models.core.Player;
import models.Session;
import util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerParser implements IParser<List<Player>> {
    private StateObserver observer;
    private final static Pattern quitPattern = Pattern.compile(CommandName.QUIT.toString());
    private final Scanner scanner;

    public PlayerParser(Scanner scanner){

        this.scanner = scanner;
    }

    @Override
    public Object checkInputCorrectness(String pattern, String errorMessage, String question) {
        Pattern playerCountPattern = Pattern.compile(pattern);
        System.out.println(question);
        String line = scanner.nextLine();
        Matcher matcher = quitPattern.matcher(line);
        if (matcher.matches()){
            observer.update("Session terminated");
            return null;
        }
        matcher = playerCountPattern.matcher(line);
        while (!matcher.find()) {
            System.out.println(errorMessage);
            System.out.println(question);
            matcher = playerCountPattern.matcher(scanner.nextLine());
        }
        return matcher.group(0);
    }

    @Override
    public List<Player> parse() {
        Object playerCountInput = this.checkInputCorrectness(
                Regex.COUNT_AND_GOLD.toString(),
                ErrorMessage.PLAYER_COUNT_INVALID.toString(),
                Communication.PLAYER_COUNT_QUESTION.toString());
        if (playerCountInput == null) {
            return null;
        }
        int playerCount = Integer.parseInt((String) playerCountInput);

        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++){
            Object playerNameInput = this.checkInputCorrectness(
                    Regex.PLAYER_NAME.toString(),
                    ErrorMessage.PLAYER_NAME_INVALID.toString(),
                    Communication.PLAYER_NAME_PROMPT.toString() + i + CoreString.COLON_STRING);
            if (playerNameInput == null){
                return null;
            }
            playerNames.add((String) playerNameInput);
        }


        int[] money = this.parseGold(scanner);
        List<Player> parsedPlayers = new ArrayList<>();
        for (String playerName: playerNames) {
            parsedPlayers.add(new Player(playerName, money[0], money[1]));
        }
        return parsedPlayers;
    }

    private int[] parseGold(Scanner scanner){
        // TODO: initial gold should not be bigger than winning gold
        Object initialGoldObject = this.checkInputCorrectness(
                Regex.COUNT_AND_GOLD.toString(),
                ErrorMessage.INITIAL_GOLD_QUANTITY_INVALID.toString(),
                Communication.INITIAL_GOLD_QUESTION.toString());
        if (initialGoldObject == null) {
            return null;
        }

        Object winningGoldObject = this.checkInputCorrectness(
                Regex.COUNT_AND_GOLD.toString(),
                ErrorMessage.WINNING_GOLD_QUANTITY_INVALID.toString(),
                Communication.WINNING_GOLD_QUESTION.toString());
        if (winningGoldObject == null) {
            return null;
        }

        return new int[] {Integer.parseInt((String) initialGoldObject),
                Integer.parseInt((String) winningGoldObject)};
    }

    @Override
    public void addObserver(StateObserver observer){
        this.observer = observer;
    }
}
