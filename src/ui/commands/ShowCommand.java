package ui.commands;

import ui.Command;
import ui.CommandArgument;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShowCommand extends Command {
    private final static Pattern ARGUMENTS_PATTERN = Pattern.compile("(barn|market|board)");
    private final static int MIN_VALID_ARGUMENT_COUNT = 1;
    private final static int MAX_VALID_ARGUMENT_COUNT = 1;
    public ShowCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        LinkedList<String> commandArgs = this.commandArguments.stream().map(CommandArgument::getValue)
                .collect(Collectors.toCollection(LinkedList::new));
        if (this.isArgumentInvalid(commandArgs.getLast(),
                ARGUMENTS_PATTERN,
                INVALID_ARGUMENT_NAME,
                commandArgs.getFirst())) {
            return false;
        }

        return true;
    }
}
