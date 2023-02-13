package util;

import models.Session;
import ui.Command;
import ui.CommandArgument;
import ui.CommandParseResult;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandParser implements IParser {
    private static final int FIRST_ARGUMENT = 1;

    @Override
    public boolean checkInputCorrectness() {
        return false;
    }

    @Override
    public CommandParseResult parseResult(Session session, Scanner scanner) {
        // check if input null, emptyString or whatever random stuff it can hold
        List<String> splittedInput = Arrays.stream(scanner.nextLine()
                .split(StringsUtilEnum.WHITESPACE_STRING.toString())).toList();
        String commandName = splittedInput.stream().findFirst().orElse(null);
        List<CommandArgument> commandArguments = splittedInput.stream().skip(FIRST_ARGUMENT).toList()
                .stream().map(CommandArgument::new).toList();
        return new CommandParseResult(session, commandName, commandArguments);
    }
}
