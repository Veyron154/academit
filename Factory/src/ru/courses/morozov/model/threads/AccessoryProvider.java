package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.FactoryManager;

public class AccessoryProvider implements Runnable {
    private FactoryManager manager;
    private long preparationTime;

    public AccessoryProvider(FactoryManager manager, long preparationTime) {
        this.manager = manager;
        this.preparationTime = preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(preparationTime);
                manager.addToAccessoriesStorage();
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
