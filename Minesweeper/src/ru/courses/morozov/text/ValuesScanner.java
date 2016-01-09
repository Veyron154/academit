package ru.courses.morozov.text;

import java.util.Locale;
import java.util.Scanner;

public class ValuesScanner {
    public int scan(String printedString, int minValue, int maxValue) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println(printedString);
        while (true) {
            int tmpValue = scanner.nextInt();
            if (tmpValue >= minValue && tmpValue <= maxValue) {
                return tmpValue;
            }
            System.out.printf("Значение должно находиться в пределах от %d до %d%n", minValue, maxValue);
        }
    }
}
