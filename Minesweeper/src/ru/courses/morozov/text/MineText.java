package ru.courses.morozov.text;

import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.model.Record;
import ru.courses.morozov.model.TableOfRecords;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MineText {
    private GridOfMines grid;
    private Timer timer;
    private int timerCount;
    private final String PATH_TO_TABLE = "Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin";
    private TableOfRecords tableOfRecords = new TableOfRecords(PATH_TO_TABLE);

    public MineText() {
    }

    public void run() {
        grid = new GridOfMines();
        grid.mining();
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        createTimer();
        timer.start();
        int row;
        int column;
        while (!grid.isWin() && !grid.isLose()) {
            print();
            while (true) {
                System.out.println("Введите индекс ячейки по вертикали: ");
                row = scanner.nextInt();
                if (row >= 0 && row < grid.getCountOfColumns()) {
                    break;
                }
                System.out.println("Индекс ячейки по вертикали должен быть в пределах от 0 до " +
                        (grid.getCountOfColumns() - 1));
            }
            while (true) {
                System.out.println("Введите индекс ячейки по горизрнтали: ");
                column = scanner.nextInt();
                if (column >= 0 && column < grid.getCountOfRows()) {
                    break;
                }
                System.out.println("Индекс ячейки по горизонтали должен быть в пределах от 0 до " +
                        (grid.getCountOfRows() - 1));
            }
            grid.open(row, column);
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
            } catch (IOException e1) {
                System.out.println("Результат не сохранён");
            }
        }
    }

    private void print() {
        System.out.print("  |");
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            System.out.printf("%2d|", i);
        }
        for (int i = 0; i < grid.getCountOfRows(); ++i) {
            System.out.printf("\n%2d|", i);
            for (int j = 0; j < grid.getCountOfColumns(); ++j) {
                if (!grid.isOpened(i, j)) {
                    System.out.print("  |");
                } else if (grid.isMined(i, j)) {
                    System.out.print(" *|");
                } else {
                    System.out.print(" " + grid.getIndex(i, j) + "|");
                }
            }
        }
        System.out.println();
    }

    private void printLose() {
        System.out.print("  |");
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            System.out.printf("%2d|", i);
        }
        for (int i = 0; i < grid.getCountOfRows(); ++i) {
            System.out.printf("\n%2d|", i);
            for (int j = 0; j < grid.getCountOfColumns(); ++j) {
                if (grid.isMined(i, j)) {
                    System.out.print(" *|");
                } else if (!grid.isOpened(i, j)) {
                    System.out.print("  |");
                } else if (grid.getIndex(i, j) == 0) {
                    System.out.print(" .|");
                } else {
                    System.out.print(" " + grid.getIndex(i, j) + "|");
                }
            }
        }
        System.out.println();
    }

    private void createTimer() {
        timer = new Timer(1000, e -> timerCount += 1);
    }
}
