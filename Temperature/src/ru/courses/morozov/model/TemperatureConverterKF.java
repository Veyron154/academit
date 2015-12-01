package ru.courses.morozov.model;

public class TemperatureConverterKF implements TemperatureConverter{

    public double convert(double inputTemperature) {
        return (inputTemperature - KELVIN_COEFFICIENT) * FAHRENHEIT_COEFFICIENT_1 + FAHRENHEIT_COEFFICIENT_2;
    }
}
