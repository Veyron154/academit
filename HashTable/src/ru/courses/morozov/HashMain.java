package ru.courses.morozov;

import java.util.ArrayList;

public class HashMain {
    public static void main(String[] args) {
        HashTable<String> ht = new HashTable<String>();

        ht.add("qwe");
        System.out.println(ht.getSize());
        System.out.println(ht.contains("qwe"));
        ht.remove("qwr");
        System.out.println(ht.getSize());
        System.out.println(ht.contains("qwe"));

        int index = 0;

        for(String s : ht){
            if(s.equals("qwe"))
            index += 1;
        }
        System.out.println(index);
    }
}
