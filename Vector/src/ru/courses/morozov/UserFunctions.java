package ru.courses.morozov;

/**
 * Created by Veyron on 18.10.2015.
 */
public class UserFunctions {
    static final double EPSILON = 1e-4;

    public static boolean testToEquality(double number1, double number2) {
        return Math.abs(number1 - number2) < EPSILON;
    }
}
