package ru.courses.morozov.model;

public interface TemperatureConverter {
    final double KELVIN_COEFFICIENT = 273.15;
    final double FAHRENHEIT_COEFFICIENT_1 = 1.8;
    final double FAHRENHEIT_COEFFICIENT_2 = 32;

    double convert(double inputTemperature);
}
