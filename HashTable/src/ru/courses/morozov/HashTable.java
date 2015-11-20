package ru.courses.morozov;
import java.util.*;

public class HashTable<T> implements Collection<T> {
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

    private int getIndex(Object object) {
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
        this.size -= 1;
        return hashTable[getIndex(object)].remove(object);
    }

    public boolean contains(Object object) {
        int index = getIndex(object);
        return this.hashTable[index] != null && this.hashTable[index].contains(object);
    }

    @SuppressWarnings("NullableProblems")
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

    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType", "NullableProblems"})
    public <T> T[] toArray(T[] inputArray) {
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

    @SuppressWarnings("NullableProblems")
    public boolean removeAll(Collection<?> inputCollection) {
        if (inputCollection == null) {
            return false;
        }
        for (Object element : inputCollection) {
            if (this.contains(element)) {
                this.remove(element);
            }
        }
        return true;
    }

    @SuppressWarnings("NullableProblems")
    public boolean retainAll(Collection<?> inputCollection) {
        if (inputCollection == null) {
            return false;
        }
        Object[] tmpArray = this.toArray();
        for (Object aTmpArray : tmpArray) {
            if (!inputCollection.contains(aTmpArray)) {
                this.remove(aTmpArray);
            }
        }
        return true;
    }

    @SuppressWarnings("NullableProblems")
    public boolean containsAll(Collection<?> inputCollection) {
        for (Object element : inputCollection) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("NullableProblems")
    public boolean addAll(Collection<? extends T> inputCollection) {
        if (inputCollection == null) {
            return false;
        }
        for (T element : inputCollection) {
            this.add(element);
        }
        return true;
    }

    @SuppressWarnings("NullableProblems")
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int countOfObjects = 0;
            private int indexInList = 0;
            private int indexInArray = 0;

            public boolean hasNext() {
                return countOfObjects < size;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T object = null;
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
