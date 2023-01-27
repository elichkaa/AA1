package util;

import models.MainSystem;
import ui.CommandArgument;
import ui.CommandParseResult;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputParser(){}

    public static CommandParseResult parseUserInputToCommand(MainSystem system){
        // check if input null, emptyString or whatever random stuff it can hold
        // magic String
        List<String> splittedInput = Arrays.stream(SCANNER.nextLine().split(" ")).toList();
        String commandName = splittedInput.stream().findFirst().orElse(null);
        // 1 is magic number
        List<CommandArgument> commandArguments = splittedInput.stream().skip(1).toList().stream().map(CommandArgument::new).toList();
        return new CommandParseResult(system, commandName, commandArguments);
    }
}
