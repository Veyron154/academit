package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.FactoryManager;
import ru.courses.morozov.model.IdCreator;
import ru.courses.morozov.model.spares.Accessory;
import ru.courses.morozov.model.spares.Body;
import ru.courses.morozov.model.spares.Car;
import ru.courses.morozov.model.spares.Engine;

public class Worker implements Runnable {
    private FactoryManager manager;
    private Body body;
    private Engine engine;
    private Accessory accessory;
    private IdCreator idCreator;

    public Worker(FactoryManager manager, Body body, Engine engine, Accessory accessory, IdCreator idCreator) {
        this.manager = manager;
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
        this.idCreator = idCreator;
    }

    public void run() {
        try {
            Thread.sleep(10000);
            manager.addToCarStorage(new Car(body, engine, accessory, idCreator.getId()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
