package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.text.ValuesScanner;

public class OpenCellAroundFieldCommand extends Command {
    private int row;
    private int column;
    private GridOfMines grid;

    public void execute() {
        if (grid.isOpened(column, row) && grid.checkFlags(column, row)) {
            grid.openButtonsAroundLabel(column, row);
        }
    }

    public void scan() {
        ValuesScanner valuesScanner = new ValuesScanner();
        row = valuesScanner.scan("Введите вертикальный индекс ячейки: ", 0, grid.getCountOfRows() - 1);
        column = valuesScanner.scan("Введите горизонтальный индекс ячейки: ", 0, grid.getCountOfColumns() - 1);
    }

    public void setGrid(GridOfMines grid) {
        this.grid = grid;
    }
}
