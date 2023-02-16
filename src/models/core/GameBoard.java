package models.core;

import models.core.tiles.Barn;
import models.core.tiles.Field;
import models.core.tiles.Garden;

public class GameBoard {
    private final Barn barn;
    private final Garden leftGarden;
    private final Garden rightGarden;
    private final Field field;

    public GameBoard() {
        this.barn = new Barn();
        this.leftGarden = new Garden(new Coordinates(-1, 0));
        this.rightGarden = new Garden(new Coordinates(1, 0));
        this.field = new Field(new Coordinates(0, 1));
    }
}
