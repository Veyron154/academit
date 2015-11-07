package ru.courses.morozov;

/**
 * Created by Veyron on 08.11.2015.
 */
public class GaussMain {

    public static void main(String[] args) {
        double[][] array1 = {{7, 3, 1}, {4, 1, 2}, {0, 0, 0}};
        double[] array2 = {1, 7, 0};

        Matrix matrix = new Matrix(array1);
        Vector vector = new Vector(3, array2);


    }
}
