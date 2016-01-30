package ru.courses.morozov.model;

public class Car {
    private Body body;
    private Engine engine;
    private Accessory accessory;
    private int id;

    public Car(Body body, Engine engine, Accessory accessory, int id) {
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public Body getBody() {
        return this.body;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public Accessory getAccessory() {
        return this.accessory;
    }
}
