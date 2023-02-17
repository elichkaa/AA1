package ui;

import models.core.Player;
import util.ErrorMessage;
import util.Regex;
import util.Communication;
import util.CoreString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerParser implements IParser<List<Player>> {
    private StateObserver observer;
    private final Scanner scanner;

    public PlayerParser(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public Object getCorrectInputIfAvailable(String pattern, String errorMessage, String question, boolean needsParsing) {
        Pattern currentPattern = Pattern.compile(pattern);
        System.out.println(question);
        Object result = null;
        Matcher matcher;
        boolean parsingSuccessful = true;
        do {
            matcher = currentPattern.matcher(scanner.nextLine());
            if (matcher.matches()) {
                if (needsParsing) {
                    try {
                        result = Integer.parseInt(matcher.group(0));
                        parsingSuccessful = true;
                    } catch (NumberFormatException exception) {
                        System.out.println(ErrorMessage.INTEGER_ARGUMENT_INVALID);
                        parsingSuccessful = false;
                    }
                } else {
                    result = matcher.group(0);
                }
            } else {
                System.out.println(errorMessage);
            }
        } while (!matcher.matches() || !parsingSuccessful);
        return result;
    }

    @Override
    public List<Player> parse() {
        int playerCount = this.parsePlayerCount();
        if (playerCount == 0) {
            return null;
        }

        List<String> playerNames = this.parsePlayerNames(playerCount);
        if (playerNames == null || playerNames.isEmpty()) {
            return null;
        }

        int[] money = this.parseGold();
        if (money == null || money.length == 0) {
            return null;
        }
        List<Player> parsedPlayers = new ArrayList<>();
        for (String playerName : playerNames) {
            parsedPlayers.add(new Player(playerName, money[0], money[1]));
        }
        return parsedPlayers;
    }

    private int parsePlayerCount() {
        Object playerCount = this.getCorrectInputIfAvailable(
                Regex.WHOLE_POSITIVE_NUMBER.toString(),
                ErrorMessage.PLAYER_COUNT_INVALID.toString(),
                Communication.PLAYER_COUNT_QUESTION.toString(),
                true);
        if (playerCount == null) {
            return 0;
        }
        return (int) playerCount;
    }

    private List<String> parsePlayerNames(int playerCount) {
        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            Object playerNameInput = this.getCorrectInputIfAvailable(
                    Regex.PLAYER_NAME.toString(),
                    ErrorMessage.PLAYER_NAME_INVALID.toString(),
                    Communication.PLAYER_NAME_PROMPT.toString() + i + CoreString.COLON_STRING,
                    false);
            if (playerNameInput == null) {
                return null;
            }
            playerNames.add((String) playerNameInput);
        }
        return playerNames;
    }

    private int[] parseGold() {
        // TODO: initial gold should not be bigger than winning gold
        Object initialGold = this.getCorrectInputIfAvailable(
                Regex.WHOLE_NUMBER.toString(),
                ErrorMessage.INITIAL_GOLD_QUANTITY_INVALID.toString(),
                Communication.INITIAL_GOLD_QUESTION.toString(),
                true);
        if (initialGold == null) {
            return null;
        }

        Object winningGold = this.getCorrectInputIfAvailable(
                Regex.WHOLE_POSITIVE_NUMBER.toString(),
                ErrorMessage.WINNING_GOLD_QUANTITY_INVALID.toString(),
                Communication.WINNING_GOLD_QUESTION.toString(),
                true);
        if (winningGold == null) {
            return null;
        }

        return new int[]{(int) initialGold, (int) winningGold};
    }

    @Override
    public void addObserver(StateObserver observer){
        this.observer = observer;
    }
}
