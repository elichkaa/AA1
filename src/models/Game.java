package models;

import ui.Command;
import ui.CommandArgument;
import ui.NewCommand;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame{
    public Game() { }

    @Override
    public void play() {
        var args = new ArrayList<>(List.of(new CommandArgument("Hello"), new CommandArgument("Hello")));
        var command = new NewCommand("new");
    }

    @Override
    public void processInput(Command command) {

    }

    @Override
    public void requestInput() {

    }

    @Override
    public boolean hasOutput() {
        return false;
    }
}
