package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.Engine;
import ru.courses.morozov.model.FactoryManager;

public class EngineProvider implements Runnable {
    private FactoryManager manager;
    private long preparationTime;

    public EngineProvider(FactoryManager manager, long preparationTime) {
        this.manager = manager;
        this.preparationTime = preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void run() {
        int engineID = 1;
        while (true) {
            try {
                Thread.sleep(preparationTime);
                manager.addToEngineStorage(new Engine(engineID));
                ++engineID;
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
