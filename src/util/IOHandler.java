package util;

import models.core.Player;
import models.core.Vegetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class IOHandler {
    private static final String FILE_PATH = "./src/util/Pixel-Art.txt";
    private static final String PIXEL_ART_FILE_DOES_NOT_EXIST = "The file with the pixel art appears to be deleted. Restore the file.";
    private IOHandler() {}

    public static void printPixelArt(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException exception) {
            System.out.println(PIXEL_ART_FILE_DOES_NOT_EXIST);
        }
    }

    public static void printOutput(String gameOutput) {
        System.out.println(gameOutput);
    }

    public static void startTurnText(Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(String.format("It is %s's turn!", player.getPlayerName())).append(System.lineSeparator());
        List<Vegetable> grownVegetables = player.getGameBoard().getGrownVegetables();
        if (grownVegetables != null) {
            if (grownVegetables.size() == 1) {
                stringBuilder.append(String.format("%s vegetable has grown since your last turn.", 1))
                        .append(System.lineSeparator());
            } else {
                stringBuilder.append(String.format("%s vegetables has grown since your last turn.", grownVegetables.size()))
                        .append(System.lineSeparator());
            }
        }
        if (player.getGameBoard().getBarn().areVegetablesSpoiled()) {
            stringBuilder.append("The vegetables in your barn are spoiled.")
                    .append(System.lineSeparator());
        }
        System.out.print(stringBuilder);
    }

    public static void printEndgameTable(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= players.size(); i++) {
            stringBuilder.append(String.format("Player %d (%s): %d", i, players.get(i - 1).getPlayerName(),
                            players.get(i - 1).getStartingGold()))
                    .append(System.lineSeparator());
        }
        List<Player> winners = players.stream().filter(Player::isWinner).toList();
        if (winners.size() == 1) {
            stringBuilder.append(String.format("%s has won!", winners.get(0).getPlayerName()));
        } else {
            stringBuilder.append(winners.get(0).getPlayerName());
            for (int i = 1; i < winners.size(); i++) {
                if (i == winners.size() - 1) {
                    stringBuilder.append(String.format(" and %s ", winners.get(i).getPlayerName()));
                    break;
                }
                stringBuilder.append(String.format(", %s", winners.get(i).getPlayerName()));
            }
            stringBuilder.append("have won!");
        }
        System.out.print(stringBuilder);
    }

    public static void printErrorMessage(String errorMessage, Object... optionalMessage) {
        if (optionalMessage.length != 0) {
            System.out.printf(errorMessage + System.lineSeparator(), optionalMessage);
        } else {
            System.out.printf(errorMessage + System.lineSeparator());
        }
    }
}
