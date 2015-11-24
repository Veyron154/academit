package ru.courses.morozov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class HashMain {
    public static void main(String[] args) {
        HashTable<String> ht = new HashTable<String>();

        ht.add("qwe");
        ht.add("123");
        ht.add("asd");
        ht.add("zxc");

        ht.remove("asd");

        HashTable<String> ht2 = new HashTable<String>();
        ht2.add("123");
        ht2.add("asd");

        System.out.println(ht.containsAll(ht2));
        ht.removeAll(ht2);
        System.out.println(Arrays.toString(ht.toArray()));
        ht.addAll(ht2);
        System.out.println(Arrays.toString(ht.toArray()));
        ht.retainAll(ht2);
        System.out.println(Arrays.toString(ht.toArray()));

        String[] u = {"hj", "op", "]["};

        System.out.println(Arrays.toString(ht.toArray(u)));

        System.out.println(ht.contains("123"));

        HashTable<String> ht3 = new HashTable<String>();

        System.out.println(ht.addAll(ht3));
    }
}
