package ru.courses.morozov;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] hashTable;
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

    private int getIndex(Object object) {
        return object.hashCode() % this.tableSize;
    }

    public boolean add(E object) {
        int index = getIndex(object);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<E>();
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
        if (this.contains(object)) {
            this.size -= 1;
        }
        return hashTable[getIndex(object)].remove(object);
    }

    public boolean contains(Object object) {
        int index = getIndex(object);
        return this.hashTable[index] != null && this.hashTable[index].contains(object);
    }

    @NotNull
    public Object[] toArray() {
        Object[] tmpArray = new Object[size];
        int index = 0;
        for (int i = 0; i < tableSize; ++i) {
            if (this.hashTable[i] == null) {
                continue;
            }
            if (this.hashTable[i].size() == 0) {
                continue;
            }
            for (int j = 0; j < this.hashTable[i].size(); ++j) {
                tmpArray[index] = this.hashTable[i].get(j);
                ++index;
            }
        }
        return tmpArray;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public <T> T[] toArray(@NotNull T[] inputArray) {
        int index = 0;
        for (T anA : inputArray) {
            if (anA == null) {
                break;
            }
            index += 1;
        }

        if ((index + this.size) > inputArray.length) {
            T[] tmpArray = inputArray;
            inputArray = (T[]) new Object[(index + this.size)];
            System.arraycopy(tmpArray, 0, inputArray, 0, tmpArray.length);
        }

        for (int i = 0; i < this.size; ++i) {
            inputArray[(index + i)] = (T) this.toArray()[i];
        }
        return inputArray;
    }

    public boolean removeAll(@NotNull Collection<?> inputCollection) {
        int tmpSize = this.size;
        for (Object element : inputCollection) {
            this.remove(element);
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
