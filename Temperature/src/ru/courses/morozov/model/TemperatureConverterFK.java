package ru.courses.morozov.model;

public class TemperatureConverterFK implements TemperatureConverter{

    public double convert(double inputTemperature) {
        return (inputTemperature - FAHRENHEIT_COEFFICIENT_2) * (1 / FAHRENHEIT_COEFFICIENT_1) + KELVIN_COEFFICIENT;
    }
}
