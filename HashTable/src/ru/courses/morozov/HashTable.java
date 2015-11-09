package ru.courses.morozov;

import java.util.ArrayList;

public class HashTable<T> {
    private ArrayList[] hashTable;
    private T object;
    private int arraySize = 128;

    public HashTable() {
        this.hashTable = new ArrayList[this.arraySize];
    }

    public HashTable(int arraySize) {
        this.arraySize = arraySize;
        this.hashTable = new ArrayList[arraySize];
    }

    public void setObject(T object) {
        this.object = object;
        int index = object.hashCode() % this.arraySize;
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<T>();
        }
        hashTable[index].add(object);
    }

    public int getCountOfElements() {
        int countOfElements = 0;
        for (ArrayList aHashTable : this.hashTable) {
            if (aHashTable != null) {
                countOfElements += aHashTable.size();
            }
        }
        return countOfElements;
    }
}
