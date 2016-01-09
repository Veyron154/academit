package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMines;

import java.util.Locale;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("Введите вертикальный индекс ячейки: ");
        while (true) {
            int tmpRow = scanner.nextInt();
            if (tmpRow >= 0 && tmpRow < grid.getCountOfRows()) {
                row = tmpRow;
                break;
            }
            System.out.println("Индекс должен находиться в пределах от 0 до " + (grid.getCountOfRows() - 1));
        }
        System.out.println("Введите горизонтальный индекс ячейки: ");
        while (true) {
            int tmpColumn = scanner.nextInt();
            if (tmpColumn >= 0 && tmpColumn < grid.getCountOfColumns()) {
                column = tmpColumn;
                break;
            }
            System.out.println("Индекс должен находиться в пределах от 0 до " + (grid.getCountOfRows() - 1));
        }
    }

    public void setGrid(GridOfMines grid) {
        this.grid = grid;
    }
}
