package models.parsing;

import models.core.Player;
import models.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerParser implements IParser<List<Player>> {
    @Override
    public boolean checkInputCorrectness() {
        return false;
    }

    @Override
    public IParseResult<List<Player>> parse(Session session, Scanner scanner) {
        List<Player> players = new ArrayList<>();
        List<String> names = new ArrayList<>();
        names.add(scanner.nextLine());
        names.add(scanner.nextLine());
        return null;
    }
}
