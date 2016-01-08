package ru.courses.morozov.text;

import ru.courses.morozov.model.GridOfMines;

public class Determinant {
    private GridOfMines grid;

    public Determinant(GridOfMines grid) {
        this.grid = grid;
    }

    public void createCommand(int commandIndex) {
        Command command = NamesOfCommands.values()[commandIndex].getCommand();
        command.setGrid(grid);
        command.scan();
        command.execute();
    }
}
