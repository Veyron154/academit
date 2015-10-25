package ru.courses.morozov;

/**
 * Created by Veyron on 25.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        double[][] array = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix = new Matrix(array);

        System.out.println(matrix.toString());
    }

}
