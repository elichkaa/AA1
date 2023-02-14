package models;

import models.core.Game;
import models.core.Player;
import models.parsing.IParseResult;
import models.parsing.IParser;
import ui.Command;
import ui.commands.NewCommand;
import ui.commands.QuitCommand;
import util.CommandName;
import models.parsing.CommandParser;
import models.parsing.PlayerParser;
import util.IOHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Session {
    private boolean sessionState = true;
    private List<Command> allCommands = new ArrayList<>();
    public Session() {}

    public void init() {
        IOHandler.printPixelArt();
        this.initializeCommands();
        Scanner scanner = new Scanner(System.in);
        Game game = this.initializeGame(scanner);

        CommandParser commandParser = new CommandParser();
        while (this.sessionState){
            Command command = commandParser.parse(this, scanner).getResult();
            this.executeCommand(command);
            if (command.getCommandName().equals(CommandName.QUIT.toString())){
                this.terminateSession();
            }
            if (!game.hasOutput()){
                command = commandParser.parse(this, scanner).getResult();
                game.processInput(command);
            }
        }
        scanner.close();
    }

    private Game initializeGame(Scanner scanner){
        PlayerParser playerParser = new PlayerParser();
        List<IParseResult<Player>> parsed =  playerParser.parseAll(this, scanner);
        if (parsed == null) {
            return null;
        }
        return new Game(5, parsed.stream().map(IParseResult::getResult).toList());
    }

    private void executeCommand(Command command){
        try {
            command.execute();
        } catch (NullPointerException nullPointerException){
            System.out.println("Command was null and could not be executed.");
        }

    }

    public void terminateSession(){
        this.sessionState = false;
    }

    private void initializeCommands(){
        this.allCommands = Arrays.asList(new NewCommand(CommandName.NEW.toString()),
                new QuitCommand(CommandName.QUIT.toString()));
    }

    public List<Command> getAllCommands() {
        return allCommands;
    }


}
