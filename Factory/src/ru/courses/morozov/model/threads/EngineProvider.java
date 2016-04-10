package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.IdCreator;
import ru.courses.morozov.model.spares.Engine;
import ru.courses.morozov.model.FactoryManager;

public class EngineProvider implements Runnable {
    private FactoryManager manager;
    private long preparationTime;
    private IdCreator idCreator;

    public EngineProvider(FactoryManager manager, long preparationTime, IdCreator idCreator) {
        this.manager = manager;
        this.preparationTime = preparationTime;
        this.idCreator = idCreator;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(preparationTime);
                manager.addToEngineStorage(new Engine(idCreator.getId()));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
