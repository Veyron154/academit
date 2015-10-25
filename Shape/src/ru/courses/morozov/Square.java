package ru.courses.morozov;

/**
 * Created by Veyron on 23.10.2015.
 */
public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getWidth() {
        return this.sideLength;
    }

    public double getHeight() {
        return this.sideLength;
    }

    public double getArea() {
        return Math.pow(this.sideLength, 2);
    }

    public String toString() {
        return "Square. L = " + this.sideLength;
    }
}
