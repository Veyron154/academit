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

    public boolean putSpare(T spare){
        synchronized (lock){
            while (storage.size() >= capacity){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(spare);
            ++producedCount;
            lock.notifyAll();
            return true;
        }
    }

    public T getSpare(){
        synchronized (lock) {
            while (storage.size() <= 0){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T withdrawSpare = storage.get(storage.size() - 1);
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
