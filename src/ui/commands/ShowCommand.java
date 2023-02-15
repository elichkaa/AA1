package ui.commands;

import ui.Command;
import ui.CommandArgument;
import util.ErrorMessage;
import util.Regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowCommand extends Command {
    private final static Pattern argumentsPattern = Pattern.compile(Regex.SHOW_COMMAND_ARGS.toString());
    private final static int VALID_ARGUMENT_COUNT = 1;
    private final static int INVALID_ARGUMENT_COUNT = 2;
    private final static String BARN_ARGUMENT = "barn";
    private final static String MARKET_ARGUMENT = "market";
    private final static String BOARD_ARGUMENT = "board";
    public ShowCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        if (!this.areArgumentsValid(INVALID_ARGUMENT_COUNT, VALID_ARGUMENT_COUNT)){
            return false;
        }

        CommandArgument targetToShow = this.commandArguments.stream().findFirst().orElse(null);
        Matcher matcher = argumentsPattern.matcher(targetToShow.getValue());
        if (matcher.matches()){
            switch (targetToShow.getValue()) {
                case BARN_ARGUMENT:

                    break;
                case MARKET_ARGUMENT:

                    break;
                case BOARD_ARGUMENT:

                    break;
            }
            return true;
        } else {
            this.printErrorMessage(ErrorMessage.INVALID_ARGUMENT_NAME.toString(), targetToShow.getValue());
            return false;
        }
    }
}
