package ru.courses.morozov;

public class HashMain {
    public static void main(String[] args) {
        HashTable<java.io.Serializable> ht = new HashTable<java.io.Serializable>();

        System.out.println(ht.getSize());
        ht.add(127);
        System.out.println(ht.getSize());
        ht.add("qwe");
        System.out.println(ht.getSize());
        ht.add('r');
        System.out.println(ht.getSize());
        System.out.println(ht.contains("qwe"));
        ht.removeAll();
        System.out.println(ht.getSize());
        System.out.println(ht.contains("qwe"));
    }
}
