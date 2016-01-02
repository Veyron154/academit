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

public class MineText {/*
    private GridOfMines grid;
    private Timer timer;
    private int timerCount;
    private final String PATH_TO_TABLE = "Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin";
    private TableOfRecords tableOfRecords = new TableOfRecords(PATH_TO_TABLE);
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private int row;
    private int column;

    public MineText() {
    }

    public void run() {
        grid = new GridOfMines();
        //grid.mining();
        createTimer();
        timer.start();
        while (!grid.isWin() && !grid.isLose()) {
            print();
            switch (printMenu()){
                case 1:
                    openCell();
            }
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

    private int printMenu(){
        System.out.println("Выберите действие:");
        System.out.println("1 - Открыть ячейку.");
        System.out.println("2 - Поставить флаг.");
        System.out.println("3 - Поставить вопрос.");
        System.out.println("4 - Открыть ячейки вокруг поля.");
        System.out.println("5 - Перезапустить игру.");
        System.out.println("6 - Новая игра.");
        System.out.println("7 - Таблица рекордов.");
        System.out.println("8 - Информация.");
        System.out.println("9 - Выход.");
        int menuIndex = scanner.nextInt();
        while (true){
            if(menuIndex > 0 && menuIndex <= 9){
                return menuIndex;
            }
            System.out.println("Введён не верный индекс.");
        }
    }

    private void openCell(){
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
    }*/
}
