package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.FileLogger;
import ru.courses.morozov.model.Logger;
import ru.courses.morozov.model.spares.Car;
import ru.courses.morozov.model.FactoryManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dealer implements Runnable {
    private FactoryManager manager;
    private int id;
    private long purchaseTime;
    private boolean logRecording;
    private Logger logger;

    public Dealer(FactoryManager manager, int id, long purchaseTime, boolean logRecording) {
        this.manager = manager;
        this.id = id;
        this.purchaseTime = purchaseTime;
        String pathToLogFile = "Factory/src/ru/courses/morozov/resources/log.txt";
        this.logRecording = logRecording;
        this.logger = new FileLogger(pathToLogFile);
    }

    public Dealer(FactoryManager manager, int id, int purchaseTime, String pathToLogFile, boolean logRecording) {
        this.manager = manager;
        this.id = id;
        this.purchaseTime = purchaseTime;
        this.logRecording = logRecording;
        this.logger = new FileLogger(pathToLogFile);
    }

    public void setPurchaseTime(int purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(purchaseTime);
                Car boughtCar = manager.getCarFromStorage();
                if (logRecording) {
                    String loggingString = String.format(new SimpleDateFormat("dd.MM.yyyy hh.mm.ss").format(new Date())
                                    + ", Dealer %d, Auto %d (Body %d, Engine %d, Accessory %d)%n", id,
                            boughtCar.getID(), boughtCar.getBody().getID(), boughtCar.getEngine().getID(),
                            boughtCar.getAccessory().getID());
                    logger.write(loggingString);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
