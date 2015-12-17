package ru.courses.morozov.model;

import java.io.*;
import java.util.*;

public class TableOfRecords {
    public static void serialize(SortedMap<Integer, String> map) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
                ("Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin"))) {
            outputStream.writeObject(map);
        }
    }

    @SuppressWarnings("unchecked")
    public static TreeMap<Integer, String> deserialize() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream
                ("Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin"))) {
            return (TreeMap<Integer, String>) inputStream.readObject();
        }
    }

    public static String tableToString() throws IOException, ClassNotFoundException {
        TreeMap<Integer, String> tmpMap = new TreeMap<>(TableOfRecords.deserialize());
        int tmpSize = tmpMap.size();
        if (tmpMap.isEmpty()) {
            return "Рекордов ещё нет!";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("     Время:       Дата:")
                    .append(System.lineSeparator());
            for (int i = 1; i <= 5 && i <= tmpSize; ++i) {
                builder.append(String.format("%d. %3d сек       ", i, tmpMap.firstKey()))
                        .append(tmpMap.remove(tmpMap.firstKey()))
                        .append(System.lineSeparator());
            }
            return builder.toString();
        }
    }
}
