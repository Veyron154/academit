package ru.courses.morozov.model;

import ru.courses.morozov.model.spares.Accessory;
import ru.courses.morozov.model.spares.Body;
import ru.courses.morozov.model.spares.Car;
import ru.courses.morozov.model.spares.Engine;
import ru.courses.morozov.model.threads.*;
import ru.courses.morozov.view.FactoryFrame;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FactoryManager {
    private FactoryFrame frame;

    private final Object carStorageLock;
    private final Object accessoriesProvidersLock;
    private final Object workersLock;
    private final Object dealersLock;

    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoriesStorage;
    private Storage<Car> carStorage;

    private ExecutorService executorService;

    private int bodyStorageCapacity;
    private int engineStorageCapacity;
    private int accessoriesStorageCapacity;
    private int carStorageCapacity;
    private int countOfAccessoriesProviders;
    private int countOfWorkers;
    private int countOfDealers;

    private int countOfRequests;
    private int carBoughtCount;

    private BodyProvider bodyProvider;
    private EngineProvider engineProvider;
    private AccessoryProvider accessoryProvider;
    private Dealer[] dealers;
    private Controller controller;

    private ConfigReader configReader;
    private boolean logRecording;

    public FactoryManager() {
        frame = new FactoryFrame(this);

        carStorageLock = new Object();
        accessoriesProvidersLock = new Object();
        workersLock = new Object();
        dealersLock = new Object();

        countOfRequests = 0;
        carBoughtCount = 0;

        configReader = new ConfigReader();
        setFactoryOptions();

        bodyStorage = new Storage<>(bodyStorageCapacity);
        engineStorage = new Storage<>(engineStorageCapacity);
        accessoriesStorage = new Storage<>(accessoriesStorageCapacity);
        carStorage = new Storage<>(carStorageCapacity);

        IdCreator accessoryIdCreator = new IdCreator();

        bodyProvider = new BodyProvider(this, TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfBodySlider()),
                new IdCreator());
        engineProvider = new EngineProvider(this, TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfEngineSlider()),
                new IdCreator());
        accessoryProvider = new AccessoryProvider(this,
                TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfAccessoriesSlider()), accessoryIdCreator);
        dealers = new Dealer[countOfDealers];
        controller = new Controller(this);

        executorService = Executors.newFixedThreadPool(countOfWorkers);
    }

    public void start() {
        frame.start();

        Thread bodyProviderThreat = new Thread(bodyProvider);
        bodyProviderThreat.start();

        Thread engineProviderThreat = new Thread(engineProvider);
        engineProviderThreat.start();

        for (int i = 0; i < countOfAccessoriesProviders; ++i) {
            Thread accessoryProviderThread = new Thread(accessoryProvider);
            accessoryProviderThread.start();
        }

        for (int i = 0; i < countOfDealers; ++i) {
            dealers[i] = new Dealer(this, i + 1, TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfDealerSlider()),
                    logRecording);
            Thread dealerThread = new Thread(dealers[i]);
            dealerThread.start();
        }

        Thread controllerThread = new Thread(controller);
        controllerThread.start();
    }

    public void addToBodyStorage(Body body) throws InterruptedException {
        bodyStorage.putSpare(body);
        frame.setBodyProducedText(Integer.toString(bodyStorage.getProducedCount()));
        frame.setBodyStorageText(Integer.toString(bodyStorage.getSize()));
    }

    public void addToEngineStorage(Engine engine) throws InterruptedException {
        engineStorage.putSpare(engine);
        frame.setEngineProducedText(Integer.toString(engineStorage.getProducedCount()));
        frame.setEngineStorageText(Integer.toString(engineStorage.getSize()));
    }

    public void addToAccessoriesStorage(Accessory accessory) throws InterruptedException {
        synchronized (accessoriesProvidersLock) {
            accessoriesStorage.putSpare(accessory);
            frame.setAccessoriesProducedText(Integer.toString(accessoriesStorage.getProducedCount()));
            frame.setAccessoriesStorageText(Integer.toString(accessoriesStorage.getSize()));
        }
    }

    public void addToCarStorage(Car car) throws InterruptedException {
        synchronized (workersLock) {
            carStorage.putSpare(car);
            --countOfRequests;
            frame.setInProductionText(Integer.toString(countOfRequests));
            frame.setDealersStorageText(Integer.toString(carStorage.getSize()));
            carStorageLock.notifyAll();
        }
    }

    public Car getCarFromStorage() throws InterruptedException {
        synchronized (dealersLock) {
            Car boughtCar = carStorage.getSpare();
            ++carBoughtCount;
            frame.setDealersBoughtText(Integer.toString(carBoughtCount));
            frame.setDealersStorageText(Integer.toString(carStorage.getSize()));
            return boughtCar;
        }
    }


    public void requestToFactory() throws InterruptedException {
        synchronized (carStorageLock) {
            while (carStorage.getSize() >= carStorageCapacity) {
                carStorageLock.wait();
            }
            while (countOfRequests + carStorage.getSize() < carStorageCapacity) {
                executorService.submit(new Worker(this, bodyStorage.getSpare(), engineStorage.getSpare(),
                        accessoriesStorage.getSpare(), new IdCreator()));
                ++countOfRequests;
                frame.setBodyStorageText(Integer.toString(bodyStorage.getSize()));
                frame.setEngineStorageText(Integer.toString(engineStorage.getSize()));
                frame.setAccessoriesStorageText(Integer.toString(accessoriesStorage.getSize()));
                frame.setInProductionText(Integer.toString(countOfRequests));
                carStorageLock.notifyAll();
            }
        }
    }

    public void setBodyProviderPreparationTime(int preparationTime) {
        bodyProvider.setPreparationTime(preparationTime);
    }

    public void setEngineProviderPreparationTime(int preparationTime) {
        engineProvider.setPreparationTime(preparationTime);
    }

    public void setAccessoryProviderPreparationTime(int preparationTime) {
        accessoryProvider.setPreparationTime(preparationTime);
    }

    public void setDealerPurchaseTime(int purchaseTime) {
        for (int i = 0; i < countOfDealers; ++i) {
            dealers[i].setPurchaseTime(purchaseTime);
        }
    }

    public void setFactoryOptions() {
        try {
            configReader.read();
        } catch (IOException e) {
            frame.showConfigNoFindMessage();
        }
        bodyStorageCapacity = configReader.getBodyStorageCapacity();
        engineStorageCapacity = configReader.getEngineStorageCapacity();
        accessoriesStorageCapacity = configReader.getAccessoryStorageCapacity();
        carStorageCapacity = configReader.getCarStorageCapacity();
        countOfAccessoriesProviders = configReader.getAccessoryProviders();
        countOfWorkers = configReader.getWorkers();
        countOfDealers = configReader.getDealers();
        logRecording = configReader.isLogSale();
    }
}
