package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.spares.Body;
import ru.courses.morozov.model.FactoryManager;

public class BodyProvider implements Runnable {
    private FactoryManager manager;
    private long preparationTime;

    public BodyProvider(FactoryManager manager, long preparationTime) {
        this.manager = manager;
        this.preparationTime = preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void run() {
        int bodyID = 1;
        while (true) {
            try {
                Thread.sleep(preparationTime);
                manager.addToBodyStorage(new Body(bodyID));
                ++bodyID;
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
