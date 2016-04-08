package ru.courses.morozov.model;

import java.util.ArrayList;

public class Storage<T> {
    private ArrayList<T> storage;
    private int capacity;
    private int producedCount;
    private final Object lock;

    public Storage(int capacity, Object lock){
        storage = new ArrayList<>();
        this.capacity = capacity;
        this.lock = lock;
        producedCount = 0;
    }

    public boolean putSpare(T spare) throws InterruptedException{
        synchronized (lock){
            while (storage.size() >= capacity){
                lock.wait();
            }
            storage.add(spare);
            ++producedCount;
            lock.notifyAll();
            return true;
        }
    }

    public T getSpare() throws InterruptedException{
        synchronized (lock) {
            while (storage.size() <= 0){
                lock.wait();
            }
            T withdrawSpare = storage.remove(storage.size() - 1);
            lock.notifyAll();
            return withdrawSpare;
        }
    }

    public int getSize(){
        return this.storage.size();
    }

    public int getProducedCount(){
        return this.producedCount;
    }
}
