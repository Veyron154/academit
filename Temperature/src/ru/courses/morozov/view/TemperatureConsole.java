package ru.courses.morozov.view;

import ru.courses.morozov.model.DeterminantConverter;
import ru.courses.morozov.model.TemperatureScale;

import java.util.Locale;
import java.util.Scanner;

public class TemperatureConsole {
    private Scanner scanner = new Scanner(System.in).useLocale(Locale.US);


    public void run() {
        System.out.println("Введите температуру: ");
        double inputTemperature = scanner.nextDouble();

        TemperatureScale scaleFrom = TemperatureScale
                .values()[scanScale("Выберите номер шкалы, из которой надо перевести: ")];
        TemperatureScale scaleTo = TemperatureScale
                .values()[scanScale("Выберите номер шкалы, в которую надо перевести: ")];

        System.out.println("*****");
        System.out.println("Начальное значение: " + inputTemperature);
        System.out.println("Начальная шкала:    " + scaleFrom);
        System.out.println("Конечная шкала:     " + scaleTo);
        System.out.println("Результат:          " + DeterminantConverter.determine(scaleFrom, scaleTo,
                inputTemperature));
        System.out.println("*****");
    }

    private int scanScale(String printedString) {
        System.out.println(printedString);
        for (int i = 0; i < TemperatureScale.values().length; ++i) {
            System.out.println(i + " - " + TemperatureScale.values()[i]);
        }
        int scaleIndex;
        while (true) {
            scaleIndex = scanner.nextInt();
            if (scaleIndex < TemperatureScale.values().length && scaleIndex >= 0) {
                return scaleIndex;
            }
            System.out.println("Введён не корректный номер");
        }
    }
}
