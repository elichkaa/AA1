package util;

import models.Player;
import models.Session;

import java.util.Scanner;

public class PlayerParser implements IParser {
    @Override
    public boolean checkInputCorrectness() {
        return false;
    }

    @Override
    public IParseResult parseResult(Session session, Scanner scanner) {
        return null;
    }
}
