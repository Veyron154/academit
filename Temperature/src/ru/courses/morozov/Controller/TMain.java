package ru.courses.morozov.Controller;

import ru.courses.morozov.view.TemperatureC;

public class TMain {
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureG();
            }
        });*/
        new TemperatureC();
    }
}
