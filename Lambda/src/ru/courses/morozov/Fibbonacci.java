package ru.courses.morozov;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Fibbonacci {
    public static void main(String[] args) {
        int x1 = 0;
        int x2 = 0;
        DoubleStream fibbonacci = DoubleStream.iterate(0, x -> x + 1);
        fibbonacci.limit(10).forEach(System.out::println);
    }
}
