package ui;

import models.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommandParser implements IParser<Command> {
    private final static int COMMAND_NAME = 1;
    private final static String WHITESPACE = " ";
    private final Session session;
    private final Scanner scanner;

    public CommandParser(Session session, Scanner scanner) {
        this.session = session;
        this.scanner = scanner;
    }

    @Override
    public Object getCorrectInputIfAvailable(Pattern pattern, String errorMessage, String question, boolean needsParsing) {
        return null;
    }

    @Override
    public Command parse() {
        // check if input null, emptyString or whatever random stuff it can hold

        List<String> userInput = Arrays.stream(this.scanner.nextLine()
                .split(WHITESPACE)).toList();
        String commandName = userInput.stream().findFirst().orElse(null);
        if (commandName == null) return null;
        if (commandName.equals("end")) {
            commandName += WHITESPACE + userInput.stream().skip(COMMAND_NAME).toList().get(0);
        }

        List<CommandArgument> commandArguments = userInput.stream().skip(COMMAND_NAME).map(CommandArgument::new).toList();
        return this.getCommand(commandName, commandArguments);
    }

    // setCommandArguments is accessible just from the current package
    private Command getCommand(String commandName, List<CommandArgument> commandArguments) {
        for (Command command : this.session.getAllCommands()) {
            if (command.getCommandName().equals(commandName)){
                command.setCommandArguments(commandArguments);
                return command;
            }
        }
        return null;
    }
}
