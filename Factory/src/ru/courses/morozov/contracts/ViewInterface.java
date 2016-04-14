package ru.courses.morozov.contracts;

public interface ViewInterface {
    void start();

    void setBodyStorageText(String countOnStorage);

    void setBodyProducedText(String countOfBodies);

    void setEngineStorageText(String countOnStorage);

    void setEngineProducedText(String countOfEngines);

    void setAccessoriesStorageText(String countOnStorage);

    void setAccessoriesProducedText(String countOfAccessories);

    void setDealersStorageText(String countOnStorage);

    void setDealersBoughtText(String countOfBoughtCars);

    void setInProductionText(String countOfRequests);

    int getDefaultValueOfBodySlider();

    int getDefaultValueOfEngineSlider();

    int getDefaultValueOfAccessoriesSlider();

    int getDefaultValueOfDealerSlider();

    void showConfigNoFindMessage();
}
