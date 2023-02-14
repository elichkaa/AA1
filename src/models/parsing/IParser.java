package models.parsing;

import models.Session;

import java.util.Scanner;

public interface IParser<T> {
    boolean checkInputCorrectness();

    IParseResult<T> parse(Session session, Scanner scanner);
}
