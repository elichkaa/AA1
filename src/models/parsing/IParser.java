package models.parsing;

import models.Session;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public interface IParser<T> {
   Object checkInputCorrectness(Session session, Scanner scanner, String pattern, String errorMessage, String question);

    IParseResult<T> parse(Session session, Scanner scanner);
    List<IParseResult<T>> parseAll(Session session, Scanner scanner);
}
