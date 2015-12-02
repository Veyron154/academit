package ru.courses.morozov.view;

import ru.courses.morozov.controller.TemperatureController;
import ru.courses.morozov.model.TemperatureScale;

import java.util.Locale;
import java.util.Scanner;

public class TemperatureC {
    private int indexScale1;
    private int indexScale2;

    public TemperatureC() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Введите температуру: ");
        double inputTemperature = scanner.nextDouble();

        scannerScale1();
        TemperatureScale scale1 = TemperatureScale.values()[indexScale1];
        scannerScale2();
        TemperatureScale scale2 = TemperatureScale.values()[indexScale2];

        System.out.println("*****");
        System.out.println("Начальное значение: " + inputTemperature);
        System.out.println("Начальная шкала:    " + scale1);
        System.out.println("Конечная шкала:     " + scale2);
        System.out.println("Результат:          " + (TemperatureController.controller(scale1, scale2, inputTemperature)));
        System.out.println("*****");
    }

    private void scannerScale1() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Выберите номер шкалы, из которой надо перевести: ");
        for (int i = 0; i < TemperatureScale.values().length; ++i) {
            System.out.println(i + " - " + TemperatureScale.values()[i]);
        }
        int tmpIndex = scanner.nextInt();
        if (tmpIndex >= TemperatureScale.values().length || tmpIndex < 0) {
            System.out.println("Введён не корректный номер");
            scannerScale1();
        } else {
            indexScale1 = tmpIndex;
        }
    }

    private void scannerScale2() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Выберите номер шкалы, в которую надо перевести: ");
        for (int i = 0; i < TemperatureScale.values().length; ++i) {
            System.out.println(i + " - " + TemperatureScale.values()[i]);
        }
        int tmpIndex = scanner.nextInt();
        if (tmpIndex >= TemperatureScale.values().length || tmpIndex < 0) {
            System.out.println("Введён не корректный номер");
            scannerScale2();
        } else {
            indexScale2 = tmpIndex;
        }
    }
}
