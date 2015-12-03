package ru.courses.morozov.model;

public interface TemperatureConverter {

    double convertToCelsius(double inputTemperature);

    double convertFromCelsius(double outputTemperature);
}
