package ru.courses.morozov;

import java.util.Locale;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class InfiniteStream {
    public static void main(String[] args) {
        DoubleStream infiniteStream = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt);

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Ввведите количество корней");
        int count = scanner.nextInt();

        infiniteStream.limit(count).forEach(System.out::println);
    }
}
