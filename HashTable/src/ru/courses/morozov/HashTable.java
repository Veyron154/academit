package ru.courses.morozov;

import java.util.ArrayList;

public class HashTable<T> {
    private ArrayList[] hashTable;
    private int tableSize = 128;

    public HashTable() {
        this.hashTable = new ArrayList[this.tableSize];
    }

    public HashTable(int arraySize) {
        this.tableSize = arraySize;
        this.hashTable = new ArrayList[arraySize];
    }

    private int getIndex(T object) {
        return object.hashCode() % this.tableSize;
    }

    public void add(T object) {
        int index = getIndex(object);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList();
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

    public void deleteObject(T object) {
        int index = getIndex(object);
        hashTable[index].remove(object);
    }

    public boolean checkAvailability(T object) {
        int index = getIndex(object);
        if (this.hashTable[index] == null) {
            return false;
        }
        for (int i = 0; i < this.hashTable[index].size(); ++i) {
            if (object.equals(this.hashTable[index].get(i))) {
                return true;
            }
        }
        return false;
    }
}
