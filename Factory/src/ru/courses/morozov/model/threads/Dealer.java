package ru.courses.morozov.model.threads;

import ru.courses.morozov.model.spares.Car;
import ru.courses.morozov.model.FactoryManager;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dealer implements Runnable {
    private FactoryManager manager;
    private int id;
    private long purchaseTime;
    private String pathToLogFile;
    private boolean logRecording;

    public Dealer(FactoryManager manager, int id, long purchaseTime, boolean logRecording) {
        this.manager = manager;
        this.id = id;
        this.purchaseTime = purchaseTime;
        pathToLogFile = "Factory/src/ru/courses/morozov/resources/log.txt";
        this.logRecording = logRecording;
    }

    public Dealer(FactoryManager manager, int id, int purchaseTime, String pathToLogFile, boolean logRecording) {
        this.manager = manager;
        this.id = id;
        this.purchaseTime = purchaseTime;
        this.pathToLogFile = pathToLogFile;
        this.logRecording = logRecording;
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
                    File logFile = new File(pathToLogFile);
                    try {
                        try (FileWriter writer = new FileWriter(logFile, true)) {
                            writer.write(String.format(new SimpleDateFormat("dd.MM.yyyy hh.mm.ss").format(new Date()) +
                                            ", Dealer %d, Auto %d (Body %d, Engine %d, Accessory %d)%n", id,
                                    boughtCar.getID(), boughtCar.getBody().getID(), boughtCar.getEngine().getID(),
                                    boughtCar.getAccessory().getID()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
