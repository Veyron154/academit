package ru.courses.morozov;
import java.util.*;


public class HashTable<T> implements Collection<T>{
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

    public int getSize() {
        return this.size;
    }

    public boolean remove(Object object) {
        if (this.hashTable[getIndex(object)] == null){
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
    public Object[] toArray(){
        Object[] tmpArray = new Object[size];
        int index = 0;
        for(int i = 0; i < tableSize; ++i){
            if(this.hashTable[i] == null){
                continue;
            }
            if(this.hashTable[i].size() == 0){
                continue;
            }
            for(int j = 0; j < this.hashTable[i].size(); ++j){
                tmpArray[index] = this.hashTable[i].get(j);
                ++index;
            }
        }
        return tmpArray;
    }

    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T> T[] toArray(T[] a){
        int index = 0;
        for (T anA : a) {
            if (anA == null) {
                break;
            }
            index += 1;
        }

        System.out.println(a.length);
        System.out.println(index);
        System.out.println(this.size);
        if((index + this.size) > a.length){
            T[] newA = a;
            a = (T[])new Object[(index + this.size)];
            System.arraycopy(newA, 0, a, 0, newA.length);
        }

        System.out.println(a.length);
        System.out.println(Arrays.toString(a));
        for(int i = 0; i < this.size; ++i){
            a[(index + i)] = (T)this.toArray()[i];
        }
        return a;
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
                    if (hashTable[i].size() == 0){
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
