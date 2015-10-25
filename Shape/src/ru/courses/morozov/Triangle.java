package ru.courses.morozov;

/**
 * Created by Veyron on 23.10.2015.
 */
public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double getWidth() {
        return Math.max(Math.max(this.x1, this.x2), this.x3) - Math.min(Math.min(this.x1, this.x2), this.x3);
    }

    public double getHeight() {
        return Math.max(Math.max(this.y1, this.y2), this.y3) - Math.min(Math.min(this.y1, this.y2), this.y3);
    }

    public double getArea() {
        double halfOfPerimeter = (getSideLength(this.x1, this.x2, this.y1, this.y2) +
                getSideLength(this.x2, this.x3, this.y2, this.y3) +
                getSideLength(this.x1, this.x3, this.y1, this.y3)) / 2;

        return Math.sqrt(halfOfPerimeter * (halfOfPerimeter - getSideLength(this.x1, this.x2, this.y1, this.y2)) *
                (halfOfPerimeter - getSideLength(this.x2, this.x3, this.y2, this.y3)) *
                (halfOfPerimeter - getSideLength(this.x1, this.x3, this.y1, this.y3)));
    }

    private double getSideLength(double x1, double x2, double y1, double y2) {
        return (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

    public String toString() {
        return String.format("Triangle. L1 = %f, L2 = %f, L3 = %f", getSideLength(this.x1, this.x2, this.y1, this.y2),
                getSideLength(this.x2, this.x3, this.y2, this.y3), getSideLength(this.x1, this.x3, this.y1, this.y3));
    }
}
