package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.text.commands.Command;

import java.util.Locale;
import java.util.Scanner;

public class SetFlagCommand extends Command {
    private int row;
    private int column;
    private GridOfMines grid;

    public void execute() {
        grid.setFlag(column, row, true);
        grid.setMark(column, row, false);
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
