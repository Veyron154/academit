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

    public void setObject(T object) {
        int index = object.hashCode() % this.tableSize;
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
        int index = object.hashCode() % this.tableSize;
        hashTable[index].remove(object);
    }
}
