package ui;

import models.Session;
import models.parsing.IParseResult;
import util.CoreString;
import java.util.List;

public class CommandParseResult implements IParseResult<Command> {
    private final Session session;
    private final String commandName;
    private final List<CommandArgument> arguments;

    public CommandParseResult(Session session, String commandName, List<CommandArgument> arguments){
        this.session = session;
        this.commandName = commandName;
        this.arguments = arguments;
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

    @Override
    public String toString() {
        return this.commandName + CoreString.WHITESPACE_STRING +
                String.join(CoreString.WHITESPACE_STRING.toString(),
                        this.arguments.stream().map(CommandArgument::getValue).toList());
    }
}
