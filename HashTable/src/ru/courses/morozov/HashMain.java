package ru.courses.morozov;

public class HashMain {
    public static void main(String[] args) {
        HashTable<java.io.Serializable> ht = new HashTable<java.io.Serializable>();

        System.out.println(ht.getCountOfElements());
        ht.setObject(127);
        System.out.println(ht.getCountOfElements());
        ht.setObject("qwe");
        System.out.println(ht.getCountOfElements());

        System.out.println(ht.checkAvailability("qwe"));
    }
}
