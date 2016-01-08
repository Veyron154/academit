package ru.courses.morozov.text;

import ru.courses.morozov.model.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MineText {
    private Scanner scanner;
    private GridOfMines grid;
    private Timer timer;
    private int timerCount;
    private TableOfRecords tableOfRecords;
    private Determinant determinant;

    public MineText() {
        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        grid = new GridOfMines();
        determinant = new Determinant(grid);
        timerCount = 0;
        final String PATH_TO_TABLE = "Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin";
        final int DEFAULT_CAPACITY_OF_TABLE = 5;
        try {
            tableOfRecords = new TableOfRecords(PATH_TO_TABLE, DEFAULT_CAPACITY_OF_TABLE, false);
        } catch (TableOfRecordsLoadException | TableOfRecordSaveException e) {
            try {
                tableOfRecords = new TableOfRecords(PATH_TO_TABLE, DEFAULT_CAPACITY_OF_TABLE, true);
                System.out.println("Создан новый файл таблицы рекордов");
            } catch (TableOfRecordSaveException | TableOfRecordsLoadException e1) {
                System.out.println("Ошибка создания таблицы рекордов");
            }
        }
    }

    public void run() {
        createTimer();
        timer.start();
        while (!grid.isWin() && !grid.isLose()) {
            printGrid();
            determinant.createCommand(printMenu());
        }
        if (grid.isLose()) {
            timer.stop();
            printLose();
            System.out.println("*Поражение!*");
        }
        if (grid.isWin()) {
            timer.stop();
            System.out.println("*Победа!*");
            System.out.println("Ваше время: " + timerCount + " сек.");
            tableOfRecords.add(new Record(timerCount, new SimpleDateFormat("dd.MM.yyyy hh.mm")
                    .format(new Date())));
            try {
                tableOfRecords.save();
            } catch (TableOfRecordSaveException e) {
                System.out.println("Ошибка сохранения результата.");
            }
        }
    }

    private int printMenu() {
        System.out.println("Выберите действие:");
        for (int i = 0; i < NamesOfCommands.values().length; ++i) {
            System.out.println(i + " - " + NamesOfCommands.values()[i].getPrintedString());
        }
        while (true) {
            int indexOfCommand = scanner.nextInt();
            if (indexOfCommand >= 0 && indexOfCommand < NamesOfCommands.values().length) {
                return indexOfCommand;
            }
            System.out.println("Индекс должен быть от 0 до " + (NamesOfCommands.values().length - 1));
        }
    }

    private void printGrid() {
        System.out.print("  |");
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            System.out.printf("%2d|", i);
        }
        for (int i = 0; i < grid.getCountOfRows(); ++i) {
            System.out.printf("\n%2d|", i);
            for (int j = 0; j < grid.getCountOfColumns(); ++j) {
                if (grid.isFlagged(j, i)) {
                    System.out.print(" F|");
                } else if (grid.isMarked(j, i)) {
                    System.out.print(" ?|");
                } else if (!grid.isOpened(j, i)) {
                    System.out.print("  |");
                } else if (grid.isMined(j, i)) {
                    System.out.print(" *|");
                } else if (grid.isFlagged(j, i)) {
                    System.out.print(" F|");
                } else {
                    System.out.printf(" %d|", grid.getIndex(j, i));
                }
            }
        }
        System.out.println();
    }

    private void createTimer() {
        timer = new Timer(1000, e -> timerCount += 1);
    }

    private void printLose() {
        System.out.print("  |");
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            System.out.printf("%2d|", i);
        }
        for (int i = 0; i < grid.getCountOfRows(); ++i) {
            System.out.printf("\n%2d|", i);
            for (int j = 0; j < grid.getCountOfColumns(); ++j) {
                if (grid.isMined(j, i)) {
                    System.out.print(" *|");
                } else if (grid.isFlagged(j, i)) {
                    System.out.print(" F|");
                } else if (grid.isMarked(j, i)) {
                    System.out.print(" ?|");
                } else if (!grid.isOpened(j, i)) {
                    System.out.print("  |");
                } else if (grid.isFlagged(j, i)) {
                    System.out.print(" F|");
                } else {
                    System.out.printf(" %d|", grid.getIndex(j, i));
                }
            }
        }
        System.out.println();
    }
}
