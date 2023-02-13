package util;

import models.Session;

import java.util.Scanner;

public interface IParser {
    boolean checkInputCorrectness();

    IParseResult parseResult(Session session, Scanner scanner);
}
