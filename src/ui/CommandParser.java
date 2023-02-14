package ui;

import models.Session;
import models.parsing.IParser;
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
    public Command parse(Session session, Scanner scanner) {
        // check if input null, emptyString or whatever random stuff it can hold
        List<String> splittedInput = Arrays.stream(scanner.nextLine()
                .split(CoreString.WHITESPACE_STRING.toString())).toList();
        String commandName = splittedInput.stream().findFirst().orElse(null);
        List<CommandArgument> commandArguments = splittedInput.stream().skip(FIRST_ARGUMENT).toList()
                .stream().map(CommandArgument::new).toList();
        return this.getCommand(session, commandName, commandArguments);
    }

    // setCommandArguments is accessible just from the current package
    private Command getCommand(Session session, String commandName, List<CommandArgument> commandArguments) {
        for (Command command : session.getAllCommands()) {
            if (command.getCommandName().equals(commandName)){
                command.setCommandArguments(commandArguments);
                return command;
            }
        }
        return null;
    }

    @Override
    public List<Command> parseAll(Session session, Scanner scanner) {
        return null;
    }
}
