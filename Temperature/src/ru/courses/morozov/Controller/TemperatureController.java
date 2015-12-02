package ru.courses.morozov.Controller;

import ru.courses.morozov.model.TemperatureConverter;
import ru.courses.morozov.model.TemperatureConverterF;
import ru.courses.morozov.model.TemperatureConverterK;
import ru.courses.morozov.model.TemperatureScale;

import java.util.HashMap;
import java.util.Map;

public class TemperatureController {
    private static Map<TemperatureScale, TemperatureConverter> mapOfConverters = new HashMap<>();

    static {
        mapOfConverters.put(TemperatureScale.KELVIN, new TemperatureConverterK());
        mapOfConverters.put(TemperatureScale.FAHRENHEIT, new TemperatureConverterF());
    }

    public static double controller(TemperatureScale scale1, TemperatureScale scale2, double inputTemperature) {
        if (scale1.equals(scale2)) {
            return inputTemperature;
        }
        if (scale1.equals(TemperatureScale.CELSIUS)) {
            return mapOfConverters.get(scale2).convertFromCelsius(inputTemperature);
        }
        if (scale2.equals(TemperatureScale.CELSIUS)) {
            return mapOfConverters.get(scale1).convertToCelsius(inputTemperature);
        }



        double tmpTemperature = mapOfConverters.get(scale1).convertToCelsius(inputTemperature);
        return mapOfConverters.get(scale2).convertFromCelsius(tmpTemperature);
    }
}
