package models.parsing;

import models.Session;

import java.util.List;
import java.util.Scanner;

public class SeedParser implements IParser<Integer> {
    @Override
    public Object checkInputCorrectness(Session session, Scanner scanner, String pattern, String errorMessage, String question) {
        return null;
    }

    @Override
    public Integer parse(Session session, Scanner scanner) {
        return null;
    }

    @Override
    public List<Integer> parseAll(Session session, Scanner scanner) {
        return null;
    }
}
