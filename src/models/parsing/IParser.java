package models.parsing;

import models.Session;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public interface IParser<T> {
   Object checkInputCorrectness(Session session, Scanner scanner, String pattern, String errorMessage, String question);

    T parse(Session session, Scanner scanner);
    List<T> parseAll(Session session, Scanner scanner);
}
