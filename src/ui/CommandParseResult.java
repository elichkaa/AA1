package ui;

import models.MainSystem;
import util.StringsUtilEnum;

import java.util.List;

public class CommandParseResult {
    private MainSystem system;
    private final String commandName;
    private final List<CommandArgument> arguments;

    public CommandParseResult(MainSystem system, String commandName, List<CommandArgument> arguments){
        this.system = system;
        this.commandName = commandName;
        this.arguments = arguments;
    }

    public Command getCommand() {
        for (Command command : system.getAllCommands()) {
            if (command.getCommandName().equals(this.commandName)){
                command.setCommandArguments(this.arguments);
                return command;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        // magic Strings
        return this.commandName + StringsUtilEnum.WHITESPACE_STRING +
                String.join(StringsUtilEnum.WHITESPACE_STRING.toString(),
                        this.arguments.stream().map(CommandArgument::getValue).toList());
    }
}
