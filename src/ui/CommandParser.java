package ui;

import models.Session;
import util.CommandName;
import util.CoreString;
import util.StateObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser implements IParser<Command> {
    private StateObserver observer;
    private final static Pattern quitPattern = Pattern.compile(CommandName.QUIT.toString());
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
        List<String> splittedInput = Arrays.stream(scanner.nextLine()
                .split(CoreString.WHITESPACE_STRING.toString())).toList();
        String commandName = splittedInput.stream().findFirst().orElse(null);
        Matcher matcher = quitPattern.matcher(commandName);
        if (matcher.matches()){
            observer.update("Session terminated");
            return null;
        }
        //List<CommandArgument<?>> commandArguments = splittedInput.stream()
           //     .skip(FIRST_ARGUMENT).map().toList();
        return this.getCommand(commandName, new ArrayList<>());
    }

    // setCommandArguments is accessible just from the current package
    private Command getCommand(String commandName, List<CommandArgument<?>> commandArguments) {
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
