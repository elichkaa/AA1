package ui.commands;

import ui.Command;
import ui.CommandArgument;

public class ShowCommand extends Command {
    public ShowCommand(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute() {
        CommandArgument targetToShow = this.commandArguments.stream().findFirst().orElse(null);
        if (targetToShow != null){
            String targetValue = targetToShow.getValue();
            switch (targetValue) {
                case "barn":

                    break;
                case "board":

                    break;
                case "market":

                    break;
                default:
                    System.out.println("Such argument does not exist.");
                    break;
            }
            return true;
        }
        return false;
    }
}
