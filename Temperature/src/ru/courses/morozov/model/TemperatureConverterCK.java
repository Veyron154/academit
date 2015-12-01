package ru.courses.morozov.model;

public class TemperatureConverterCK implements TemperatureConverter{

    public double convert(double inputTemperature) {
        return inputTemperature + KELVIN_COEFFICIENT;
    }
}
