package ui;

import java.util.Scanner;

public class SeedParser implements IParser<Integer> {
    private final Scanner scanner;

    public SeedParser(Scanner scanner) {

        this.scanner = scanner;
    }

    @Override
    public Object getCorrectInputIfAvailable(String pattern, String errorMessage, String question) {
        return null;
    }

    @Override
    public Integer parse() {
        return null;
    }

    @Override
    public void addObserver(StateObserver observer) {

    }
}
