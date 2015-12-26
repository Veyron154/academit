package ru.courses.morozov.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TableOfRecords {
    private static final String PATH_TO_TABLE = "Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin";

    public static void serialize(List<Record> list) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
                (PATH_TO_TABLE))) {
            List<Record> tmpList = list.stream().sorted((a1, a2) -> a1.getTime() - a2.getTime())
                    .collect(Collectors.toList());
            outputStream.writeObject(tmpList);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Record> deserialize() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream
                (PATH_TO_TABLE))) {
            return (List<Record>) inputStream.readObject();
        }
    }

    public static String tableToString() throws IOException, ClassNotFoundException {
        List<Record> tmpList = TableOfRecords.deserialize();
        int tmpSize = tmpList.size();
        if (tmpList.isEmpty()) {
            return "Рекордов ещё нет!";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("     Время:       Дата:")
                .append(System.lineSeparator());
        for (int i = 1; i <= 5 && i <= tmpSize; ++i) {
            builder.append(String.format("%d. %3d сек       ", i, tmpList.get(i - 1).getTime()))
                    .append(tmpList.get(i - 1).getDate())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}