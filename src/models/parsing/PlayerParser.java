package models.parsing;

import models.core.Player;
import models.Session;
import util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerParser implements IParser<Player> {
    private final static Pattern quitPattern = Pattern.compile(CommandName.QUIT.toString());

    @Override
    public Object checkInputCorrectness(Session session, Scanner scanner, String pattern, String errorMessage, String question) {
        Pattern playerCountPattern = Pattern.compile(pattern);
        System.out.println(question);
        String line = scanner.nextLine();
        Matcher matcher = quitPattern.matcher(line);
        if (matcher.matches()){
            session.terminateSession();
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
    public Player parse(Session session, Scanner scanner) {
        return null;
    }

    @Override
    public List<Player> parseAll(Session session, Scanner scanner) {
        Object playerCountInput = this.checkInputCorrectness(session, scanner,
                Regex.COUNT_AND_GOLD.toString(),
                ErrorMessage.PLAYER_COUNT_INVALID.toString(),
                Communication.PLAYER_COUNT_QUESTION.toString());
        if (playerCountInput == null) {
            return null;
        }
        int playerCount = Integer.parseInt((String) playerCountInput);

        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++){
            Object playerNameInput = this.checkInputCorrectness(session, scanner,
                    Regex.PLAYER_NAME.toString(),
                    ErrorMessage.PLAYER_NAME_INVALID.toString(),
                    Communication.PLAYER_NAME_PROMPT.toString() + i + CoreString.COLON_STRING);
            if (playerNameInput == null){
                return null;
            }
            playerNames.add((String) playerNameInput);
        }

        // TODO: initial gold should not be bigger than winning gold
        Object initialGoldObject = this.checkInputCorrectness(session, scanner,
                Regex.COUNT_AND_GOLD.toString(),
                ErrorMessage.INITIAL_GOLD_QUANTITY_INVALID.toString(),
                Communication.INITIAL_GOLD_QUESTION.toString());
        if (initialGoldObject == null) {
            return null;
        }
        int initialGold = Integer.parseInt((String) initialGoldObject);

        Object winningGoldObject = this.checkInputCorrectness(session, scanner,
                Regex.COUNT_AND_GOLD.toString(),
                ErrorMessage.WINNING_GOLD_QUANTITY_INVALID.toString(),
                Communication.WINNING_GOLD_QUESTION.toString());
        if (winningGoldObject == null) {
            return null;
        }
        int winningGold = Integer.parseInt((String) winningGoldObject);

        List<Player> parsedPlayers = new ArrayList<>();
        for (String playerName: playerNames) {
            parsedPlayers.add(new Player(playerName, initialGold, winningGold));
        }
        return parsedPlayers;
    }
}
