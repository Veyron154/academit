package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.model.GridOfMinesAbsenceException;
import ru.courses.morozov.text.ValuesScanner;

public abstract class Command {
    private int row;
    private int column;
    private GridOfMines grid;

    public abstract void execute() throws GridOfMinesAbsenceException;

    protected void scan() {
        ValuesScanner valuesScanner = new ValuesScanner();
        row = valuesScanner.scan("Введите вертикальный индекс ячейки: ", 0, grid.getCountOfRows() - 1);
        column = valuesScanner.scan("Введите горизонтальный индекс ячейки: ", 0, grid.getCountOfColumns() - 1);
    }

    protected GridOfMines getGrid() {
        return this.grid;
    }

    protected int getRow() {
        return this.row;
    }

    protected int getColumn() {
        return this.column;
    }

    public void setGrid(GridOfMines grid) {
        this.grid = grid;
    }
}
