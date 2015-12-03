package ru.courses.morozov.model;

import java.util.HashMap;
import java.util.Map;

public class DeterminantConverter {
    private static Map<TemperatureScale, TemperatureConverter> mapOfConverters = new HashMap<>();

    static {
        mapOfConverters.put(TemperatureScale.KELVIN, new TemperatureConverterK());
        mapOfConverters.put(TemperatureScale.FAHRENHEIT, new TemperatureConverterF());
    }

    private DeterminantConverter(){}

    public static double determine(TemperatureScale scaleFrom, TemperatureScale scaleTo, double inputTemperature) {
        if (scaleFrom.equals(scaleTo)) {
            return inputTemperature;
        }
        if (scaleFrom.equals(TemperatureScale.CELSIUS)) {
            return mapOfConverters.get(scaleTo).convertFromCelsius(inputTemperature);
        }
        if (scaleTo.equals(TemperatureScale.CELSIUS)) {
            return mapOfConverters.get(scaleFrom).convertToCelsius(inputTemperature);
        }


        double tmpTemperature = mapOfConverters.get(scaleFrom).convertToCelsius(inputTemperature);
        return mapOfConverters.get(scaleTo).convertFromCelsius(tmpTemperature);
    }
}
