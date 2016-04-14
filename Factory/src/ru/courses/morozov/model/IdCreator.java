package ru.courses.morozov.model;

public class IdCreator {
    private int id;
    private final Object lock;

    public IdCreator(){
        id = 0;
        lock = new Object();
    }

    public int getId(){
        synchronized (lock) {
            ++id;
            return id;
        }
    }
}
