package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.FactoryManager;
import ru.courses.morozov.model.IdCreator;
import ru.courses.morozov.model.spares.Accessory;

public class AccessoryProvider implements Runnable {
    private FactoryManager manager;
    private long preparationTime;
    private IdCreator idCreator;

    public AccessoryProvider(FactoryManager manager, long preparationTime, IdCreator idCreator) {
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
                manager.addToAccessoriesStorage(new Accessory(idCreator.getId()));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
