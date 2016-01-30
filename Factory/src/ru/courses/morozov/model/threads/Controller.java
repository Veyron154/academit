package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.FactoryManager;

public class Controller implements Runnable {
    private FactoryManager manager;

    public Controller(FactoryManager manager) {
        this.manager = manager;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            manager.requestToFactory();
        }
    }
}
