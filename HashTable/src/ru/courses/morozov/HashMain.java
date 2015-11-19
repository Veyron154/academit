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

        HashTable<String> ht2 = new HashTable<String>();
        ht2.add("123");
        ht2.add("qwe");

        System.out.println(ht.containsAll(ht2));


        System.out.println(Arrays.toString(ht.toArray()));

    }
}
