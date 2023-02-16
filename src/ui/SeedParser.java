package ui;

import util.Communication;
import util.ErrorMessage;
import util.Regex;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeedParser implements IParser<Integer> {
    private final Scanner scanner;
    private final static Pattern quitPattern = Pattern.compile(Regex.QUIT.toString());
    private StateObserver observer;

    public SeedParser(Scanner scanner) {

        this.scanner = scanner;
    }

    @Override
    public Object getCorrectInputIfAvailable(String pattern, String errorMessage, String question) {
        System.out.println(question);
        String line = scanner.nextLine();
        Matcher matcher = quitPattern.matcher(line);
        if (matcher.matches()) {
            observer.update(Communication.SESSION_TERMINATED_MESSAGE.toString());
            return null;
        }
        long seed = 0L;
        boolean foundSeed = false;
        do {
            try {
                seed = Long.parseLong(line);
                foundSeed = true;
            } catch (NumberFormatException exception) {
                System.out.println(errorMessage);
                line = scanner.nextLine();
            }
        } while (!foundSeed);
        return seed;
    }

    @Override
    public Integer parse() {
        long seed = (long) this.getCorrectInputIfAvailable(null,
                ErrorMessage.INPUT_FOR_SEED_INVALID.toString(),
                Communication.SEED_PROMPT.toString());
        return new Random(seed).nextInt();
    }

    @Override
    public void addObserver(StateObserver observer) {
        this.observer = observer;
    }
}
