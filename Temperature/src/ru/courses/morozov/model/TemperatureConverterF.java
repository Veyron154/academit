package ru.courses.morozov.model;

public class TemperatureConverterF implements TemperatureConverter {
    private final double FAHRENHEIT_COEFFICIENT_1 = 1.8;
    private final double FAHRENHEIT_COEFFICIENT_2 = 32;

    public double convertToCelsius(double inputTemperature) {
        return (inputTemperature - FAHRENHEIT_COEFFICIENT_2) * (1 / FAHRENHEIT_COEFFICIENT_1);
    }

    public double convertFromCelsius(double inputTemperature) {
        return (inputTemperature * FAHRENHEIT_COEFFICIENT_1) + FAHRENHEIT_COEFFICIENT_2;
    }
}
