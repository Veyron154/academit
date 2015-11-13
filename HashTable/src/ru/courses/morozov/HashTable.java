package ru.courses.morozov;

import java.util.ArrayList;

public class HashTable<T> {
    private ArrayList<T>[] hashTable;
    private int tableSize = 128;
    private int size = 0;

    public HashTable() {
        //noinspection unchecked
        this.hashTable = new ArrayList[this.tableSize];
    }

    public HashTable(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть больше нуля");
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
        this.size += 1;
    }

    public int getSize() {
        return this.size;
    }

    public boolean remove(T object) {
        if (contains(object)) {
            int index = getIndex(object);
            hashTable[index].remove(object);
            this.size -= 1;
            return true;
        }
        return false;
    }

    public boolean contains(T object) {
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
