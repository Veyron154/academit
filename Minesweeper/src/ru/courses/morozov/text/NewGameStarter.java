package ru.courses.morozov.text;

import ru.courses.morozov.model.GridOfMines;

import java.util.Locale;
import java.util.Scanner;

public class NewGameStarter extends Command {
    private int countOfColumns;
    private int countOfRows;
    private int countOfMines;
    private GridOfMines grid;

    public void execute() {
        grid.setCountOfColumns(countOfColumns);
        grid.setCountOfRows(countOfRows);
        grid.setCountOfMines(countOfMines);
        grid.setGrid();
        grid.setFilled(false);
    }

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        final int MIN_COUNT_OF_ROWS = 9;
        final int MAX_COUNT_OF_ROWS = 24;
        final int MIN_COUNT_OF_COLUMNS = 9;
        final int MAX_COUNT_OF_COLUMNS = 30;
        final int MIN_COUNT_OF_MINES = 10;
        final int MAX_COUNT_OF_MINES = 668;

        System.out.println("Введите ширину поля: ");
        while (true) {
            int tmpCountOfColumns = scanner.nextInt();
            if (tmpCountOfColumns >= MIN_COUNT_OF_COLUMNS && tmpCountOfColumns <= MAX_COUNT_OF_COLUMNS) {
                countOfColumns = tmpCountOfColumns;
                break;
            }
            System.out.printf("Ширина поля должна быть в пределах от %d до %d\n", MIN_COUNT_OF_COLUMNS,
                    MAX_COUNT_OF_COLUMNS);
        }
        System.out.println("Введите высоту поля: ");
        while (true) {
            int tmpCountOfRows = scanner.nextInt();
            if (tmpCountOfRows >= MIN_COUNT_OF_ROWS && tmpCountOfRows <= MAX_COUNT_OF_ROWS) {
                countOfRows = tmpCountOfRows;
                break;
            }
            System.out.printf("Высота поля должна быть в пределах от %d до %d\n", MIN_COUNT_OF_ROWS, MAX_COUNT_OF_ROWS);
        }
        System.out.println("Введите число мин: ");
        while (true) {
            int tmpCountOfMines = scanner.nextInt();
            if (tmpCountOfMines >= MIN_COUNT_OF_MINES && tmpCountOfMines <= MAX_COUNT_OF_MINES) {
                countOfMines = tmpCountOfMines;
                break;
            }
            System.out.printf("Число мин должно быть в пределах от %d до %d\n", MIN_COUNT_OF_MINES, MAX_COUNT_OF_MINES);
        }
    }

    public void setGrid(GridOfMines grid) {
        this.grid = grid;
    }
}
