package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMines;

public class RestartGameCommand extends Command {
    private GridOfMines grid;

    public void execute() {
        grid.clean();
        grid.setFilled(false);
    }

    public void setGrid(GridOfMines grid) {
        this.grid = grid;
    }
}
