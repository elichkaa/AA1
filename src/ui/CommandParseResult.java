package ui;

import models.Session;
import util.StringsUtilEnum;
import java.util.List;

public class CommandParseResult {
    private final Session session;
    private final String commandName;
    private final List<CommandArgument> arguments;

    public CommandParseResult(Session session, String commandName, List<CommandArgument> arguments){
        this.session = session;
        this.commandName = commandName;
        this.arguments = arguments;
    }

    public Command getCommand() {
        for (Command command : session.getAllCommands()) {
            if (command.getCommandName().equals(this.commandName)){
                command.setCommandArguments(this.arguments);
                return command;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return this.commandName + StringsUtilEnum.WHITESPACE_STRING +
                String.join(StringsUtilEnum.WHITESPACE_STRING.toString(),
                        this.arguments.stream().map(CommandArgument::getValue).toList());
    }
}
