package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.CommandName;
import util.CoreString;
import util.ErrorMessage;
import util.Regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowCommand extends Command {
    private final static Pattern ARGUMENTS_PATTERN = Pattern.compile(Regex.SHOW_COMMAND_ARGS.toString());
    private final static int MIN_VALID_ARGUMENT_COUNT = 1;
    private final static int MAX_VALID_ARGUMENT_COUNT = 1;
    private final static String BARN_ARGUMENT = "barn";
    private final static String MARKET_ARGUMENT = "market";
    private final static String BOARD_ARGUMENT = "board";
    public ShowCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (this.isArgumentCountInvalid(CommandName.SHOW.toString(), MIN_VALID_ARGUMENT_COUNT, MAX_VALID_ARGUMENT_COUNT)) {
            return false;
        }

        CommandArgument objectToShow = this.commandArguments.stream().findFirst()
                .orElse(new CommandArgument(CoreString.EMPTY_STRING.toString()));
        Matcher matcher = ARGUMENTS_PATTERN.matcher(objectToShow.getValue());
        if (matcher.matches()) {
            switch (objectToShow.getValue()) {
                case BARN_ARGUMENT:

                    break;
                case MARKET_ARGUMENT:

                    break;
                case BOARD_ARGUMENT:

                    break;
            }
            return true;
        } else {
            this.printErrorMessage(CommandName.SHOW.toString(), ErrorMessage.INVALID_ARGUMENT_NAME.toString(), objectToShow.getValue());
            return false;
        }
    }
}
