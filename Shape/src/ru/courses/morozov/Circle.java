package ru.courses.morozov;

/**
 * Created by Veyron on 23.10.2015.
 */
public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return this.radius * 2;
    }

    public double getHeight() {
        return this.radius * 2;
    }

    public double getArea() {
        return Math.pow(this.radius, 2) * Math.PI;
    }

    public String toString() {
        return "Circle. R = " + this.radius;
    }
}
