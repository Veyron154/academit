package ru.courses.morozov.controller;

import ru.courses.morozov.model.Cell;
import ru.courses.morozov.model.GridOfMines;

import java.util.LinkedList;
import java.util.Queue;


public class Controller {
    public static void open(int row, int column, GridOfMines grid) {
        Queue<Cell> queue = new LinkedList<>();

        if (!grid.isFlagged(row, column)) {
            grid.setOpened(row, column, true);
            queue.add(grid.getCell(row, column));
        }

        while (!queue.isEmpty()) {
            Cell cell = queue.remove();

            if (cell.getIndex() == 0 && !cell.isMined()) {
                int countOfRows = grid.getCountOfRows();
                int countOfColumns = grid.getCountOfColumns();
                int rowIndex = cell.getRowIndex();
                int columnIndex = cell.getColumnIndex();

                if (rowIndex != 0 && !grid.isOpened(rowIndex - 1, columnIndex) &&
                        !grid.isFlagged(rowIndex - 1, columnIndex)) {
                    queue.add(grid.getCell(rowIndex - 1, columnIndex));
                    grid.setOpened(rowIndex - 1, columnIndex, true);
                }
                if (columnIndex != 0 && !grid.isOpened(rowIndex, columnIndex - 1) &&
                        !grid.isFlagged(rowIndex, columnIndex - 1)) {
                    queue.add(grid.getCell(rowIndex, columnIndex - 1));
                    grid.setOpened(rowIndex, columnIndex - 1, true);
                }
                if (rowIndex != countOfColumns - 1 && !grid.isOpened(rowIndex + 1, columnIndex)
                        && !grid.isFlagged(rowIndex + 1, columnIndex)) {
                    queue.add(grid.getCell(rowIndex + 1, columnIndex));
                    grid.setOpened(rowIndex + 1, columnIndex, true);
                }
                if (columnIndex != countOfRows - 1 && !grid.isOpened(rowIndex, columnIndex + 1)
                        && !grid.isFlagged(rowIndex, columnIndex + 1)) {
                    queue.add(grid.getCell(rowIndex, columnIndex + 1));
                    grid.setOpened(rowIndex, columnIndex + 1, true);
                }
                if (rowIndex != 0 && columnIndex != 0 && !grid.isOpened(rowIndex - 1, columnIndex - 1)
                        && !grid.isFlagged(rowIndex - 1, columnIndex - 1)) {
                    queue.add(grid.getCell(rowIndex - 1, columnIndex - 1));
                    grid.setOpened(rowIndex - 1, columnIndex - 1, true);
                }
                if (rowIndex != countOfColumns - 1 && columnIndex != countOfRows - 1 && !grid.isOpened(rowIndex + 1,
                        columnIndex + 1) && !grid.isFlagged(rowIndex + 1, columnIndex + 1)) {
                    queue.add(grid.getCell(rowIndex + 1, columnIndex + 1));
                    grid.setOpened(rowIndex + 1, columnIndex + 1, true);
                }
                if (rowIndex != 0 && columnIndex != countOfRows - 1 && !grid.isOpened(rowIndex - 1, columnIndex + 1)
                        && !grid.isFlagged(rowIndex - 1, columnIndex + 1)) {
                    queue.add(grid.getCell(rowIndex - 1, columnIndex + 1));
                    grid.setOpened(rowIndex - 1, columnIndex + 1, true);
                }
                if (rowIndex != countOfColumns - 1 && columnIndex != 0 && !grid.isOpened(rowIndex + 1, columnIndex - 1)
                        && !grid.isFlagged(rowIndex + 1, columnIndex - 1)) {
                    queue.add(grid.getCell(rowIndex + 1, columnIndex - 1));
                    grid.setOpened(rowIndex + 1, columnIndex - 1, true);
                }
            }
        }
    }
}

