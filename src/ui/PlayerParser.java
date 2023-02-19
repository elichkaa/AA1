package ui;

import models.core.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerParser implements IParser<List<Player>> {
    private final static Pattern PLAYER_NAME = Pattern.compile("[A-Za-z]+");
    private final static Pattern WHOLE_NUMBER = Pattern.compile("^0*[0-9]\\d*$");
    private final static Pattern WHOLE_POSITIVE_NUMBER = Pattern.compile("^0*[1-9]\\d*$");
    private final static String PLAYER_COUNT_QUESTION = "How many players?";
    private final static String INITIAL_GOLD_QUESTION = "With how much gold should each player start?";
    private final static String WINNING_GOLD_QUESTION = "With how much gold should a player win?";
    private final static String PLAYER_NAME_PROMPT = "Enter the name of player ";
    private static final String PLAYER_NAME_INVALID = "Error: Name of the player is invalid. Please only include letters.";
    private static final String PLAYER_COUNT_INVALID = "Error: Player count is invalid. Please enter a positive whole number.";
    private static final String INITIAL_GOLD_QUANTITY_INVALID = "Error: The initial gold quantity is invalid. Please enter a whole number.";
    private static final String WINNING_GOLD_QUANTITY_INVALID = "Error: The winning gold quantity is invalid. Please enter a positive whole number.";
    private static final String INTEGER_PARSING_FAILED = "Error: Argument is invalid. Please input a number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE;
    private final static String COLON = ":";
    private final Scanner scanner;

    public PlayerParser(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Object getCorrectInputIfAvailable(Pattern pattern, String errorMessage, String question, boolean needsParsing) {
        System.out.println(question);
        Object result = null;
        Matcher matcher;
        boolean parsingSuccessful = true;
        do {
            matcher = pattern.matcher(scanner.nextLine());
            if (matcher.matches()) {
                if (needsParsing) {
                    try {
                        result = Integer.parseInt(matcher.group(0));
                        parsingSuccessful = true;
                    } catch (NumberFormatException exception) {
                        System.out.println(INTEGER_PARSING_FAILED);
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
                WHOLE_POSITIVE_NUMBER,
                PLAYER_COUNT_INVALID,
                PLAYER_COUNT_QUESTION,
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
                    PLAYER_NAME,
                    PLAYER_NAME_INVALID,
                    PLAYER_NAME_PROMPT + i + COLON,
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
                WHOLE_NUMBER,
                INITIAL_GOLD_QUANTITY_INVALID,
                INITIAL_GOLD_QUESTION,
                true);
        if (initialGold == null) {
            return null;
        }

        Object winningGold = this.getCorrectInputIfAvailable(
                WHOLE_POSITIVE_NUMBER,
                WINNING_GOLD_QUANTITY_INVALID,
                WINNING_GOLD_QUESTION,
                true);
        if (winningGold == null) {
            return null;
        }

        return new int[]{(int) initialGold, (int) winningGold};
    }
}
