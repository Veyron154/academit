package ru.courses.morozov.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TableOfRecords {
    private String pathToTable;
    private List<Record> listOfRecords;
    private int capacity;

    @SuppressWarnings("unchecked")
    public TableOfRecords(String pathToTable) throws IOException, ClassNotFoundException {
        this.capacity = 5;
        this.pathToTable = pathToTable;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream
                (pathToTable))) {
            this.listOfRecords = (List<Record>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            File newTableOfRecords = new File(pathToTable);
            try {
                if (newTableOfRecords.createNewFile()) {
                    listOfRecords = new ArrayList<>();
                    save();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public TableOfRecords(String pathToTable, int capacity) throws IOException, ClassNotFoundException {
        new TableOfRecords(pathToTable);
        this.capacity = capacity;
    }

    public void add(Record record) {
        if (listOfRecords.size() < capacity) {
            this.listOfRecords.add(record);
            listOfRecords = listOfRecords.stream().sorted((a1, a2) -> a1.getTime() - a2.getTime())
                    .collect(Collectors.toList());
            return;
        }
        while (listOfRecords.size() > capacity) {
            listOfRecords.remove(listOfRecords.size() - 1);
        }
        if (record.getTime() < listOfRecords.get(capacity - 1).getTime()) {
            listOfRecords.remove(capacity - 1);
            this.listOfRecords.add(record);
            listOfRecords = listOfRecords.stream().sorted((a1, a2) -> a1.getTime() - a2.getTime())
                    .collect(Collectors.toList());
        }
    }

    public void save() throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
                (pathToTable))) {
            outputStream.writeObject(listOfRecords);
        }
    }

    public String tableToString() throws IOException, ClassNotFoundException {
        int tmpSize = listOfRecords.size();
        if (listOfRecords.isEmpty()) {
            return "Рекордов ещё нет!";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("     Время:       Дата:")
                .append(System.lineSeparator());
        for (int i = 1; i <= tmpSize; ++i) {
            builder.append(String.format("%d. %3d сек       ", i, listOfRecords.get(i - 1).getTime()))
                    .append(listOfRecords.get(i - 1).getDate())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}