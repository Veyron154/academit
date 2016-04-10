package ru.courses.morozov.model;

public class IdCreator {
    private int id;

    public IdCreator(){
        id = 0;
    }

    public int getId(){
        ++id;
        return id;
    }
}
