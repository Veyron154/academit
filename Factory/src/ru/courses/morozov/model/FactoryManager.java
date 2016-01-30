package ru.courses.morozov.model;

import ru.courses.morozov.model.threads.*;
import ru.courses.morozov.view.FactoryFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FactoryManager {
    private FactoryFrame frame;

    private final Object bodyStorageLock;
    private final Object engineStorageLock;
    private final Object accessoriesStorageLock;
    private final Object carStorageLock;

    private ArrayList<Body> bodyStorage;
    private ArrayList<Engine> engineStorage;
    private ArrayList<Accessory> accessoriesStorage;
    private ArrayList<Car> carStorage;

    private ExecutorService executorService;

    private int bodyStorageCapacity;
    private int engineStorageCapacity;
    private int accessoriesStorageCapacity;
    private int carStorageCapacity;
    private int countOfAccessoriesProviders;
    private int countOfWorkers;
    private int countOfDealers;

    private int accessoryID;
    private int carID;
    private int countOfRequests;
    private int bodyProducedCount;
    private int engineProducedCount;
    private int accessoriesProducedCount;
    private int carBoughtCount;

    private BodyProvider bodyProvider;
    private EngineProvider engineProvider;
    private AccessoryProvider accessoryProvider;
    private Dealer[] dealers;
    private Controller controller;

    private boolean logRecording;

    public FactoryManager(String pathToConfigFile) {
        frame = new FactoryFrame(this);

        bodyStorageLock = new Object();
        engineStorageLock = new Object();
        accessoriesStorageLock = new Object();
        carStorageLock = new Object();

        bodyStorage = new ArrayList<>();
        engineStorage = new ArrayList<>();
        accessoriesStorage = new ArrayList<>();
        carStorage = new ArrayList<>();

        accessoryID = 1;
        carID = 1;
        countOfRequests = 0;
        bodyProducedCount = 0;
        engineProducedCount = 0;
        accessoriesProducedCount = 0;
        carBoughtCount = 0;

        readConfigFile(pathToConfigFile);

        bodyProvider = new BodyProvider(this, TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfBodySlider()));
        engineProvider = new EngineProvider(this, TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfEngineSlider()));
        accessoryProvider = new AccessoryProvider(this,
                TimeUnit.SECONDS.toMillis(frame.getDefaultValueOfAccessoriesSlider()));
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

    public void addToBodyStorage(Body body) {
        synchronized (bodyStorageLock) {
            while (bodyStorage.size() >= bodyStorageCapacity) {
                try {
                    bodyStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            bodyStorage.add(body);
            ++bodyProducedCount;
            frame.setBodyProducedText(Integer.toString(bodyProducedCount));
            frame.setBodyStorageText(Integer.toString(bodyStorage.size()));
            bodyStorageLock.notifyAll();
        }
    }

    public void addToEngineStorage(Engine engine) {
        synchronized (engineStorageLock) {
            while (engineStorage.size() >= engineStorageCapacity) {
                try {
                    engineStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            engineStorage.add(engine);
            ++engineProducedCount;
            frame.setEngineProducedText(Integer.toString(engineProducedCount));
            frame.setEngineStorageText(Integer.toString(engineStorage.size()));
            engineStorageLock.notifyAll();
        }
    }

    public void addToAccessoriesStorage() {
        synchronized (accessoriesStorageLock) {
            while ((accessoriesStorage.size() >= accessoriesStorageCapacity)) {
                try {
                    accessoriesStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accessoriesStorage.add(new Accessory(accessoryID));
            ++accessoryID;
            ++accessoriesProducedCount;
            frame.setAccessoriesProducedText(Integer.toString(accessoriesProducedCount));
            frame.setAccessoriesStorageText(Integer.toString(accessoriesStorage.size()));
            accessoriesStorageLock.notifyAll();
        }
    }

    public Car getCarFromStorage() {
        synchronized (carStorageLock) {
            while (carStorage.size() == 0) {
                try {
                    carStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Car boughtCar = carStorage.remove(carStorage.size() - 1);
            ++carBoughtCount;
            frame.setDealersBoughtText(Integer.toString(carBoughtCount));
            frame.setDealersStorageText(Integer.toString(carStorage.size()));
            carStorageLock.notifyAll();
            return boughtCar;
        }
    }

    public void createCar() {
        Body body;
        Engine engine;
        Accessory accessory;
        synchronized (bodyStorageLock) {
            while (bodyStorage.size() == 0) {
                try {
                    bodyStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            body = bodyStorage.remove(bodyStorage.size() - 1);
            frame.setBodyStorageText(Integer.toString(bodyStorage.size()));
            bodyStorageLock.notifyAll();
        }
        synchronized (engineStorageLock) {
            while (engineStorage.size() == 0) {
                try {
                    engineStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            engine = engineStorage.remove(engineStorage.size() - 1);
            frame.setEngineStorageText(Integer.toString(engineStorage.size()));
            engineStorageLock.notifyAll();
        }
        synchronized (accessoriesStorageLock) {
            while (accessoriesStorage.size() == 0) {
                try {
                    accessoriesStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accessory = accessoriesStorage.remove(accessoriesStorage.size() - 1);
            frame.setAccessoriesStorageText(Integer.toString(accessoriesStorage.size()));
            accessoriesStorageLock.notifyAll();
        }
        synchronized (carStorageLock) {
            while (carStorage.size() >= carStorageCapacity) {
                try {
                    carStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            carStorage.add(new Car(body, engine, accessory, carID));
            ++carID;
            --countOfRequests;
            frame.setInProductionText(Integer.toString(countOfRequests));
            frame.setDealersStorageText(Integer.toString(carStorage.size()));
            carStorageLock.notifyAll();
        }
    }

    public void requestToFactory() {
        synchronized (carStorageLock) {
            while (carStorage.size() >= carStorageCapacity) {
                try {
                    carStorageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (countOfRequests < carStorageCapacity) {
                executorService.submit(new Worker(this));
                ++countOfRequests;
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

    public void readConfigFile(String pathToFile) {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
                String string = reader.readLine();
                bodyStorageCapacity = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                engineStorageCapacity = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                accessoriesStorageCapacity = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                carStorageCapacity = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                countOfAccessoriesProviders = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                countOfWorkers = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                countOfDealers = Integer.parseInt(string.substring(string.indexOf('=') + 1));
                string = reader.readLine();
                logRecording = Boolean.parseBoolean(string.substring(string.indexOf('=') + 1));
            }
        } catch (IOException e) {
            frame.showConfigNoFindMessage();
        }
    }
}
