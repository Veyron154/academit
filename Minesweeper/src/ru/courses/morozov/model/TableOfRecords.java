package ru.courses.morozov.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TableOfRecords {
    private String pathToTable;
    private List<Record> listOfRecords;

    @SuppressWarnings("unchecked")
    public TableOfRecords(String pathToTable){
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getListOfRecords(){
        return this.listOfRecords;
    }

    public void add(Record record){
        this.listOfRecords.add(record);
    }

    public void save() throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
                (pathToTable))) {
            listOfRecords = listOfRecords.stream().sorted((a1, a2) -> a1.getTime() - a2.getTime())
                    .collect(Collectors.toList());
            outputStream.writeObject(listOfRecords);
        }
    }

    @SuppressWarnings("unchecked")
    /*public List<Record> load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream
                (pathToTable))) {
            return (List<Record>) inputStream.readObject();
        }
    }*/

    public String tableToString() throws IOException, ClassNotFoundException {
        int tmpSize = listOfRecords.size();
        if (listOfRecords.isEmpty()) {
            return "Рекордов ещё нет!";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("     Время:       Дата:")
                .append(System.lineSeparator());
        for (int i = 1; i <= 5 && i <= tmpSize; ++i) {
            builder.append(String.format("%d. %3d сек       ", i, listOfRecords.get(i - 1).getTime()))
                    .append(listOfRecords.get(i - 1).getDate())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}