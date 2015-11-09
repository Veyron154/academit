package ru.courses.morozov;

import java.util.ArrayList;

public class HashTable<T> {
    private ArrayList<T>[] hashTable;
    private int tableSize = 128;

    public HashTable() {
        //noinspection unchecked
        this.hashTable = new ArrayList[this.tableSize];
    }

    public HashTable(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть больше единицы");
        }
        this.tableSize = tableSize;
        //noinspection unchecked
        this.hashTable = new ArrayList[tableSize];
    }

    private int getIndex(T object) {
        return object.hashCode() % this.tableSize;
    }

    public void add(T object) {
        int index = getIndex(object);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<T>();
        }
        hashTable[index].add(object);
    }

    public int getCountOfElements() {
        int countOfElements = 0;
        for (ArrayList hashTable : this.hashTable) {
            if (hashTable != null) {
                countOfElements += hashTable.size();
            }
        }
        return countOfElements;
    }

    public boolean remove(T object) {
        if (checkAvailability(object)) {
            int index = getIndex(object);
            hashTable[index].remove(object);
            return true;
        }
        return false;
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
