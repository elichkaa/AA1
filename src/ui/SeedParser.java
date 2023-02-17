package ui;

import util.ErrorMessage;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SeedParser implements IParser<Random> {
    private final Scanner scanner;
    private final static String SEED_PROMPT = "Please enter the seed used to shuffle the tiles:";
    private StateObserver observer;

    public SeedParser(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Object getCorrectInputIfAvailable(Pattern pattern, String errorMessage, String question, boolean needsParsing) {
        System.out.println(question);
        String line;
        int seed = 0;
        boolean foundSeed = false;
        do {
            line = scanner.nextLine();
            if (needsParsing) {
                try {
                    seed = Integer.parseInt(line);
                    foundSeed = true;
                } catch (NumberFormatException exception) {
                    System.out.println(errorMessage);
                }
            }
        } while (!foundSeed);
        return seed;
    }

    @Override
    public Random parse() {
        int seed = (int) this.getCorrectInputIfAvailable(null,
                ErrorMessage.INTEGER_ARGUMENT_INVALID.toString(),
                SEED_PROMPT,
                true);
        return new Random(seed);
    }

    @Override
    public void addObserver(StateObserver observer) {
        this.observer = observer;
    }
}
