package ru.courses.morozov.view;

import ru.courses.morozov.model.DeterminantConverter;
import ru.courses.morozov.model.TemperatureScale;

import java.util.Locale;
import java.util.Scanner;

public class TemperatureConsole {
    private int indexScale1;
    private int indexScale2;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Введите температуру: ");
        double inputTemperature = scanner.nextDouble();

        scanScale1();
        TemperatureScale scale1 = TemperatureScale.values()[indexScale1];
        scanScale2();
        TemperatureScale scale2 = TemperatureScale.values()[indexScale2];

        System.out.println("*****");
        System.out.println("Начальное значение: " + inputTemperature);
        System.out.println("Начальная шкала:    " + scale1);
        System.out.println("Конечная шкала:     " + scale2);
        System.out.println("Результат:          " + DeterminantConverter.determine(scale1, scale2, inputTemperature));
        System.out.println("*****");
    }

    private void scanScale1() {
        System.out.println("Выберите номер шкалы, из которой надо перевести: ");
        int tmpIndex = scanScale();
        if (tmpIndex >= TemperatureScale.values().length || tmpIndex < 0) {
            System.out.println("*Введён не корректный номер*");
            scanScale1();
        } else {
            indexScale1 = tmpIndex;
        }
    }

    private void scanScale2() {
        System.out.println("Выберите номер шкалы, в которую надо перевести: ");
        int tmpIndex = scanScale();
        if (tmpIndex >= TemperatureScale.values().length || tmpIndex < 0) {
            System.out.println("Введён не корректный номер");
            scanScale2();
        } else {
            indexScale2 = tmpIndex;
        }
    }

    private int scanScale() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        for (int i = 0; i < TemperatureScale.values().length; ++i) {
            System.out.println(i + " - " + TemperatureScale.values()[i]);
        }
        return scanner.nextInt();
    }
}
