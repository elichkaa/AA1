package util;

import models.MainSystem;
import ui.CommandArgument;
import ui.CommandParseResult;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int FIRST_ARGUMENT_COUNT = 1;

    private InputParser(){}

    public static CommandParseResult parseUserInputAsCommand(MainSystem system) {
        // check if input null, emptyString or whatever random stuff it can hold
        List<String> splittedInput = Arrays.stream(SCANNER.nextLine()
                .split(StringsUtilEnum.WHITESPACE_STRING.toString())).toList();
        String commandName = splittedInput.stream().findFirst().orElse(null);
        List<CommandArgument> commandArguments = splittedInput.stream().skip(FIRST_ARGUMENT_COUNT).toList()
                .stream().map(CommandArgument::new).toList();
        return new CommandParseResult(system, commandName, commandArguments);
    }
}
