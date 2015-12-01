package ru.courses.morozov.model;

public class TemperatureConverterCF implements TemperatureConverter{

    public double convert(double inputTemperature) {
        return (inputTemperature * FAHRENHEIT_COEFFICIENT_1) + FAHRENHEIT_COEFFICIENT_2;
    }
}
