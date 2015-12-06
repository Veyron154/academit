package ru.courses.morozov;

import java.util.Date;

public class Person {
    private String name;
    private int yearOfBirth;

    public Person(String name, int yearOfBirth){
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName(){
        return this.name;
    }

    public int getYearOfBirth(){
        return this.yearOfBirth;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDateOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }

    public int getAge(){
        final int THIS_YEAR = 2015;
        return THIS_YEAR - yearOfBirth;
    }
}
