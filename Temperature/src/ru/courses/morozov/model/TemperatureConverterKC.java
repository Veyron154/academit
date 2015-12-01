package ru.courses.morozov.model;

public class TemperatureConverterKC implements TemperatureConverter{

    public double convert(double inputTemperature) {
        return inputTemperature - KELVIN_COEFFICIENT;
    }
}
