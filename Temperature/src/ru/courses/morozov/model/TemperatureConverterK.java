package ru.courses.morozov.model;

public class TemperatureConverterK implements TemperatureConverter {
    double KELVIN_COEFFICIENT = 273.15;

    public double convertFromCelsius(double inputTemperature) {
        return inputTemperature + KELVIN_COEFFICIENT;
    }

    public double convertToCelsius(double inputTemperature) {
        return inputTemperature - KELVIN_COEFFICIENT;
    }
}
