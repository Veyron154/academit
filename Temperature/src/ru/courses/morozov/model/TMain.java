package ru.courses.morozov.model;

import ru.courses.morozov.view.TemperatureConsole;
import ru.courses.morozov.view.TemperatureGraphic;

public class TMain {
    public static void main(String[] args) {
        TemperatureGraphic tg = new TemperatureGraphic();
        //tg.run();
        TemperatureConsole tc = new TemperatureConsole();
        tc.run();
    }
}
