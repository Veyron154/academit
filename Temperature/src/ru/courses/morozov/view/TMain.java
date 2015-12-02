package ru.courses.morozov.view;

import ru.courses.morozov.view.TemperatureC;
import ru.courses.morozov.view.TemperatureG;

import javax.swing.*;

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
