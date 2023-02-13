package ui;

import models.Session;
import util.IParseResult;
import util.StringsUtilEnum;
import java.util.List;

public class CommandParseResult implements IParseResult {
    private final Session session;
    private final String commandName;
    private final List<CommandArgument> arguments;

    public CommandParseResult(Session session, String commandName, List<CommandArgument> arguments){
        this.session = session;
        this.commandName = commandName;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return this.commandName + StringsUtilEnum.WHITESPACE_STRING +
                String.join(StringsUtilEnum.WHITESPACE_STRING.toString(),
                        this.arguments.stream().map(CommandArgument::getValue).toList());
    }

    @Override
    public Command getResult() {
        for (Command command : session.getAllCommands()) {
            if (command.getCommandName().equals(this.commandName)){
                command.setCommandArguments(this.arguments);
                return command;
            }
        }
        return null;
    }
}
