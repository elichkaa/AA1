package util;

import java.util.List;

public final class MessagePrinter {
    private final static String TURN_NAME_MESSAGE = "It is %s's turn!";
    //TODO: check if %d is the right thingy
    private final static String ONE_VEGETABLE_GROWTH_MESSAGE = "%d vegetable has grown since your last turn.";
    private final static String MULTIPLE_VEGETABLE_GROWTH_MESSAGE = "%d vegetables have grown since your last turn.";
    private final static String SPOILED_VEGETABLES_MESSAGE = "The vegetables in your barn are spoiled.";
    private final static String PLAYER_STATS_LINE = "Player %d (%s): %d";
    private final static String SOLD_SINGLE_VEGETABLE_MESSAGE = "You have sold %d vegetable for %d gold.%n";
    private final static String SOLD_MULTIPLE_VEGETABLES_MESSAGE = "You have sold %d vegetables for %d gold.%n";
    private final static String ONE_WINNER_MESSAGE = "%s has won!";
    private final static String MULTIPLE_WINNERS_MESSAGE = "have won!";

    private MessagePrinter() {
    }

    public static void startTurnText(String playerName, int grownVegetables, boolean areVegetablesSpoiled) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(String.format(TURN_NAME_MESSAGE, playerName)).append(System.lineSeparator());

        if (grownVegetables == 1) {
            stringBuilder.append(String.format(ONE_VEGETABLE_GROWTH_MESSAGE, 1))
                    .append(System.lineSeparator());
        } else if (grownVegetables > 1) {
            stringBuilder.append(String.format(MULTIPLE_VEGETABLE_GROWTH_MESSAGE, grownVegetables))
                    .append(System.lineSeparator());
        }

        if (areVegetablesSpoiled) {
            stringBuilder.append(SPOILED_VEGETABLES_MESSAGE)
                    .append(System.lineSeparator());
        }
        System.out.print(stringBuilder);
    }

    public static void printEndgameTable(List<String> playerNames, List<Integer> playerMoney, List<String> winners) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= playerNames.size(); i++) {
            stringBuilder.append(String.format(PLAYER_STATS_LINE, i,
                            playerNames.get(i - 1),
                            playerMoney.get(i - 1)))
                    .append(System.lineSeparator());
        }
        if (winners.size() == 1) {
            stringBuilder.append(String.format(ONE_WINNER_MESSAGE, winners.get(0)));
        } else {
            stringBuilder.append(winners.get(0));
            // TODO: maybe refactor so its prettier?
            for (int i = 1; i < winners.size(); i++) {
                if (i == winners.size() - 1) {
                    stringBuilder.append(String.format(" and %s ", winners.get(i)));
                    break;
                }
                stringBuilder.append(String.format(", %s", winners.get(i)));
            }
            stringBuilder.append(MULTIPLE_WINNERS_MESSAGE);
        }
        System.out.print(stringBuilder);
    }

    // TODO: maybe create a stucture printer for all the barns, tostrings, etc.
    public static void printOutput(String output) {
        System.out.println(output);
    }

    public static void printMessageAfterSell(int count, int gold) {
        if (count == 1) {
            System.out.printf(SOLD_SINGLE_VEGETABLE_MESSAGE, count, gold);
        } else {
            System.out.printf(SOLD_MULTIPLE_VEGETABLES_MESSAGE, count, gold);
        }
    }

    public static void printMessageAfterBuyingTile(String tileName, int gold) {
        System.out.printf("You have bought a %s for %d gold.%n", tileName, gold);
    }
}
