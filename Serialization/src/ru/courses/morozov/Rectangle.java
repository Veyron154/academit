package ru.courses.morozov;

import java.io.Serializable;

public class Rectangle implements Serializable {
    private int width;
    private int height;
    private transient int area;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
        this.area = (width * height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getArea() {
        return this.area;
    }

    public void setWidth(int width) {
        this.width = width;
        this.area = width * this.height;
    }

    public void setHeight(int height) {
        this.height = height;
        this.area = height * this.width;
    }
}
