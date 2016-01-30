package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.FactoryManager;

public class Worker implements Runnable {
    private FactoryManager manager;

    public Worker(FactoryManager manager) {
        this.manager = manager;
    }

    public void run() {
        manager.createCar();
    }
}
