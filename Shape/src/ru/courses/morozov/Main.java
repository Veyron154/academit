package ru.courses.morozov;

import java.util.ArrayList;

/**
 * Created by Veyron on 12.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        Square square = new Square(20);
        Triangle triangle = new Triangle(0, 15, 0, 0, 0, 10);
        Rectangle rectangle = new Rectangle(21, 5);
        Circle circle = new Circle(5);

        ArrayList<Shape> list = new ArrayList<Shape>();
        list.add(square);
        list.add(triangle);
        list.add(rectangle);
        list.add(circle);

        System.out.println(list.get(0).getWidth());
        System.out.println(list.get(1).getWidth());
        System.out.println(list.get(2).getWidth());
        System.out.println(list.get(3).getWidth());

        System.out.println("Фигура с максимальной шириной - " + maxWidth(list));
        System.out.println("Суммарная площадь - " + sumArea(list));
    }

    public static double sumArea(ArrayList<Shape> list) {
        double sumArea = 0;
        for (Shape aList : list) {
            sumArea += aList.getArea();
        }
        return sumArea;
    }

    public static Shape maxWidth(ArrayList<Shape> list) {
        double maxWidth = 0;
        int index = 0;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getWidth() > maxWidth) {
                maxWidth = list.get(i).getWidth();
                index = i;
            }
        }
        return list.get(index);
    }

}
