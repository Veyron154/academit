package ru.courses.morozov.model;

public interface TemperatureConverter {
    double KELVIN_COEFFICIENT = 273.15;
    double FAHRENHEIT_COEFFICIENT_1 = 1.8;
    double FAHRENHEIT_COEFFICIENT_2 = 32;

    double convertToCelsius(double inputTemperature);

    double convertFromCelsius(double outputTemperature);
}
