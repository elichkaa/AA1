package ui;

import models.Session;
import util.Communication;
import util.CoreString;
import util.Regex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser implements IParser<Command> {
    private final static int COMMAND_NAME = 1;
    private StateObserver observer;
    private final static Pattern quitPattern = Pattern.compile(Regex.QUIT.toString());
    private final Session session;
    private final Scanner scanner;

    public CommandParser(Session session, Scanner scanner){
        this.session = session;
        this.scanner = scanner;
    }

    @Override
    public Object checkInputCorrectness(String pattern, String errorMessage, String question) {
        return null;
    }

    @Override
    public Command parse() {
        // check if input null, emptyString or whatever random stuff it can hold

        List<String> userInput = Arrays.stream(this.scanner.nextLine()
                .split(CoreString.WHITESPACE_STRING.toString())).toList();
        String commandName = userInput.stream().findFirst().orElse(null);
        if (commandName == null) return null;
        Matcher matcher = quitPattern.matcher(commandName);
        if (matcher.matches()){
            observer.update(Communication.SESSION_TERMINATED_MESSAGE.toString());
            return null;
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

    @Override
    public void addObserver(StateObserver observer) {
        this.observer = observer;
    }
}
