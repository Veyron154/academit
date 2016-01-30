package ru.courses.morozov;

import ru.courses.morozov.model.FactoryManager;
import ru.courses.morozov.view.FactoryFrame;

public class FactoryMain {
    public static void main(String[] args) {
        String pathToConfigFile = "Factory/src/ru/courses/morozov/resources/config.txt";
        FactoryManager manager = new FactoryManager(pathToConfigFile);
        manager.start();
    }
}
