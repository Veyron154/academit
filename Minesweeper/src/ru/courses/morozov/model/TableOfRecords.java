package ru.courses.morozov.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TableOfRecords {
    private String pathToTable;
    private List<Record> listOfRecords;
    private int capacity;

    @SuppressWarnings("unchecked")
    public TableOfRecords(String pathToTable) throws TableOfRecordsLoadException {
        this.capacity = 5;
        this.pathToTable = pathToTable;
        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream
                    (pathToTable))) {
                this.listOfRecords = (List<Record>) inputStream.readObject();
            } catch (FileNotFoundException e) {
                throw new TableOfRecordsLoadException();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public TableOfRecords(String pathToTable, int capacity) throws TableOfRecordsLoadException {
        this(pathToTable);
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

    public void save() throws TableOfRecordSaveException {
        try {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
                    (pathToTable))) {
                outputStream.writeObject(listOfRecords);
            }
        } catch (IOException e) {
            throw new TableOfRecordSaveException();
        }
    }

    public String tableToString() {
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