package ru.courses.morozov.model;

import java.io.Serializable;

public class Record implements Serializable {
    private int time;
    private String date;

    public Record(int time, String date) {
        this.time = time;
        this.date = date;
    }

    public int getTime() {
        return this.time;
    }

    public String getDate() {
        return this.date;
    }
}
