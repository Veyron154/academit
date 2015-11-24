package ru.courses.morozov;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] hashTable;
    final private static int TABLE_SIZE = 128;
    private int size = 0;

    public HashTable() {
        //noinspection unchecked
        this.hashTable = new ArrayList[TABLE_SIZE];
    }

    public HashTable(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть больше нуля");
        }
        //noinspection unchecked
        this.hashTable = new ArrayList[tableSize];
    }

    private int getIndex(Object object) {
        return object.hashCode() % hashTable.length;
    }

    public boolean add(E object) {
        int index = getIndex(object);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }
        hashTable[index].add(object);
        this.size += 1;
        return true;
    }

    public void clear() {
        for (ArrayList<E> aHashTable : this.hashTable) {
            if (aHashTable != null) {
                aHashTable.clear();
            }
        }
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean remove(Object object) {
        if (this.hashTable[getIndex(object)] == null) {
            return false;
        }
        if (hashTable[getIndex(object)].remove(object)){
            this.size -= 1;
            return true;
        }
        return false;
    }

    public boolean contains(Object object) {
        int index = getIndex(object);
        return this.hashTable[index] != null && this.hashTable[index].contains(object);
    }

    @NotNull
    public Object[] toArray() {
        Object[] tmpArray = new Object[size];
        int index = 0;
        for (ArrayList<E> list : hashTable) {
            if (list == null) {
                continue;
            }
            if (list.size() == 0) {
                continue;
            }
            for (E element : list) {
                tmpArray[index] = element;
                ++index;
            }
        }
        return tmpArray;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T> T[] toArray(@NotNull T[] inputArray) {
        if (this.size > inputArray.length) {
            inputArray = (T[]) new Object[this.size];
        }

        for (int i = 0; i < this.size; ++i) {
            inputArray[i] = (T) this.toArray()[i];
        }

        if (inputArray.length > this.size){
            inputArray[this.size] = null;
        }
        return inputArray;
    }

    public boolean removeAll(@NotNull Collection<?> inputCollection) {
        int tmpSize = this.size;
        for (Object element : inputCollection) {
            this.remove(element);
            if(this.contains(element)){
                return removeAll(inputCollection);
            }
        }
        return tmpSize != this.size;
    }

    public boolean retainAll(@NotNull Collection<?> inputCollection) {
        Object[] tmpArray = this.toArray();
        int tmpSize = this.size;
        for (Object aTmpArray : tmpArray) {
            if (!inputCollection.contains(aTmpArray)) {
                this.remove(aTmpArray);
            }
        }
        return tmpSize != this.size;
    }

    public boolean containsAll(@NotNull Collection<?> inputCollection) {
        for (Object element : inputCollection) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(@NotNull Collection<? extends E> inputCollection) {
        int tmpSize = this.size;
        for (E element : inputCollection) {
            this.add(element);
        }
        return tmpSize != this.size;
    }

    @NotNull
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int countOfObjects = 0;
            private int indexInList = 0;
            private int indexInArray = 0;

            public boolean hasNext() {
                return countOfObjects < size;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E object = null;
                for (int i = indexInArray; i < hashTable.length; ++i) {
                    if (hashTable[i] == null) {
                        continue;
                    }
                    if (hashTable[i].size() == 0) {
                        continue;
                    }
                    object = hashTable[i].get(indexInList);
                    indexInList++;
                    if (indexInList >= hashTable[i].size()) {
                        indexInList = 0;
                        ++i;
                    }
                    countOfObjects++;
                    indexInArray = i;
                    break;
                }
                return object;
            }
        };
    }
}
