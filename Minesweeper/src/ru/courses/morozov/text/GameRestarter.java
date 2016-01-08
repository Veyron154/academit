package ru.courses.morozov.text;

import ru.courses.morozov.model.GridOfMines;

public class GameRestarter extends Command {
    private GridOfMines grid;

    public void execute() {
        grid.clean();
        grid.setFilled(false);
    }

    public void setGrid(GridOfMines grid) {
        this.grid = grid;
    }
}
