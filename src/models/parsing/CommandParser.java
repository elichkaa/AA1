package models.parsing;

import models.Session;
import ui.Command;
import ui.CommandArgument;
import ui.CommandParseResult;
import util.CoreString;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandParser implements IParser<Command> {
    private static final int FIRST_ARGUMENT = 1;

    @Override
    public Object checkInputCorrectness(Session session, Scanner scanner, String pattern, String errorMessage, String question) {
        return null;
    }

    @Override
    public IParseResult<Command> parse(Session session, Scanner scanner) {
        // check if input null, emptyString or whatever random stuff it can hold
        List<String> splittedInput = Arrays.stream(scanner.nextLine()
                .split(CoreString.WHITESPACE_STRING.toString())).toList();
        String commandName = splittedInput.stream().findFirst().orElse(null);
        List<CommandArgument> commandArguments = splittedInput.stream().skip(FIRST_ARGUMENT).toList()
                .stream().map(CommandArgument::new).toList();
        return new CommandParseResult(session, commandName, commandArguments);
    }

    @Override
    public List<IParseResult<Command>> parseAll(Session session, Scanner scanner) {
        return null;
    }
}
