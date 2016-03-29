package ru.courses.morozov.model;

import ru.courses.morozov.view.FactoryFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    private FactoryFrame frame;
    private StringBuilder builder;

    private int bodyStorageCapacity;
    private int engineStorageCapacity;
    private int accessoryStorageCapacity;
    private int carStorageCapacity;
    private int accessoryProviders;
    private int workers;
    private int dealers;
    private boolean logSale;

    public ConfigReader(FactoryFrame frame) {
        this.frame = frame;
        builder = new StringBuilder();
        int defaultBodyStorageCapacity = 20;
        int defaultEngineStorageCapacity = 20;
        int defaultAccessoryStorageCapacity = 30;
        int defaultCarStorageCapacity = 15;
        int defaultAccessoryProviders = 5;
        int defaultWorkers = 5;
        int defaultDealers = 3;

        bodyStorageCapacity = defaultBodyStorageCapacity;
        engineStorageCapacity = defaultEngineStorageCapacity;
        accessoryStorageCapacity = defaultAccessoryStorageCapacity;
        carStorageCapacity = defaultCarStorageCapacity;
        accessoryProviders = defaultAccessoryProviders;
        workers = defaultWorkers;
        dealers = defaultDealers;
        logSale = true;
    }

    public void read() {
        String pathToConfig = "Factory/src/ru/courses/morozov/resources/config.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader
                (pathToConfig))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line)
                        .append(System.lineSeparator());
            }
            bodyStorageCapacity = getIntValue("BodyStorageCapacity=");
            engineStorageCapacity = getIntValue("EngineStorageCapacity=");
            accessoryStorageCapacity = getIntValue("AccessoriesStorageCapacity=");
            carStorageCapacity = getIntValue("CarStorageCapacity=");
            accessoryProviders = getIntValue("AccessoryProviders=");
            workers = getIntValue("Workers=");
            dealers = getIntValue("Dealers=");
            logSale = getBooleanValue("LogSale=");
        } catch (IOException e) {
            frame.showConfigNoFindMessage();
        }
    }

    private int getIntValue(String string) {
        int index = builder.indexOf(string);
        int stringLength = string.length();
        return Integer.parseInt(builder.substring(index + stringLength, builder.indexOf(System.lineSeparator(),
                index)));
    }

    private boolean getBooleanValue(String string) {
        int index = builder.indexOf(string);
        int stringLength = string.length();
        return Boolean.parseBoolean(builder.substring(index + stringLength, builder.indexOf(System.lineSeparator(),
                index)));
    }

    public int getBodyStorageCapacity() {
        return bodyStorageCapacity;
    }

    public int getEngineStorageCapacity() {
        return engineStorageCapacity;
    }

    public int getAccessoryStorageCapacity() {
        return accessoryStorageCapacity;
    }

    public int getCarStorageCapacity() {
        return carStorageCapacity;
    }

    public int getAccessoryProviders() {
        return accessoryProviders;
    }

    public int getWorkers() {
        return workers;
    }

    public int getDealers() {
        return dealers;
    }

    public boolean isLogSale() {
        return logSale;
    }
}
