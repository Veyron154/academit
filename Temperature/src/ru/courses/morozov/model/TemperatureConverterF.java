package ru.courses.morozov.model;

public class TemperatureConverterF implements TemperatureConverter {

    public double convertToCelsius(double inputTemperature) {
        return (inputTemperature - FAHRENHEIT_COEFFICIENT_2) * (1 / FAHRENHEIT_COEFFICIENT_1);
    }

    public double convertFromCelsius(double inputTemperature) {
        return (inputTemperature * FAHRENHEIT_COEFFICIENT_1) + FAHRENHEIT_COEFFICIENT_2;
    }
}
