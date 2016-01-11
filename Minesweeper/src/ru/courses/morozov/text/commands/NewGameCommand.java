package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMinesAbsenceException;
import ru.courses.morozov.text.ValuesScanner;

import java.util.Locale;
import java.util.Scanner;

public class NewGameCommand extends Command {
    private int countOfColumns;
    private int countOfRows;
    private int countOfMines;

    public void execute() throws GridOfMinesAbsenceException {
        if (getGrid() == null) {
            throw new GridOfMinesAbsenceException();
        }
        scan();
        getGrid().setCountOfColumns(countOfColumns);
        getGrid().setCountOfRows(countOfRows);
        getGrid().setCountOfMines(countOfMines);
        getGrid().setGrid();
        getGrid().setFilled(false);
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

        ValuesScanner valuesScanner = new ValuesScanner();
        countOfColumns = valuesScanner.scan("Введите ширину поля: ", MIN_COUNT_OF_COLUMNS, MAX_COUNT_OF_COLUMNS);
        countOfRows = valuesScanner.scan("Введите высоту поля: ", MIN_COUNT_OF_ROWS, MAX_COUNT_OF_ROWS);
        final int MAX_PERCENT_OCCUPANCY_OF_GRID = 85;
        int maxFeasibleCountOfMines = (countOfColumns * countOfRows * MAX_PERCENT_OCCUPANCY_OF_GRID) / 100;
        int tmpCountOfMines;
        while (true) {
            tmpCountOfMines = valuesScanner.scan("Введите число мин: ", MIN_COUNT_OF_MINES, MAX_COUNT_OF_MINES);
            if (tmpCountOfMines <= maxFeasibleCountOfMines) {
                countOfMines = tmpCountOfMines;
                break;
            }
            System.out.printf("Число мин не должно превышать %d в зависимости от текущих значений ширины и высоты%n",
                    maxFeasibleCountOfMines);
        }
    }
}
