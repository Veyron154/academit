package ru.courses.morozov;
import java.util.*;


public class HashTable<T> implements Iterable{
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

    public boolean add(T object) {
        int index = getIndex(object);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<T>();
        }
        hashTable[index].add(object);
        this.size += 1;
        return true;
    }

    public void clear() {
        for (ArrayList<T> aHashTable : this.hashTable) {
            if (aHashTable != null) {
                aHashTable.clear();
            }
        }
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean remove(T object) {
        if (this.hashTable[getIndex(object)] == null){
            return false;
        }
        this.size -= 1;
        return hashTable[getIndex(object)].remove(object);
    }

    public boolean contains(T object) {
        int index = getIndex(object);
        return this.hashTable[index] != null && this.hashTable[index].contains(object);
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int countOfObjects = 0;
            private int indexOfList = 0;
            private int indexOfArray = 0;

            public boolean hasNext() {
                return countOfObjects < size;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T object = null;
                for (int i = indexOfArray; i < hashTable.length; ++i) {
                    if (hashTable[i] == null) {
                        continue;
                    }
                    object = hashTable[i].get(indexOfList);
                    indexOfList++;
                    if (indexOfList >= hashTable[i].size()) {
                        indexOfList = 0;
                        ++i;
                    }
                    countOfObjects++;
                    indexOfArray = i;
                    break;
                }
                return object;
            }
        };
    }
}
