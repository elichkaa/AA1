package ui;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SeedParser implements IParser<Random> {
    private final Scanner scanner;
    private final static String SEED_PROMPT = "Please enter the seed used to shuffle the tiles:";
    private static final String INTEGER_PARSING_FAILED = "Error: Argument is invalid. Please input a number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE;
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
                INTEGER_PARSING_FAILED,
                SEED_PROMPT,
                true);
        return new Random(seed);
    }

    @Override
    public void addObserver(StateObserver observer) {
        this.observer = observer;
    }
}
