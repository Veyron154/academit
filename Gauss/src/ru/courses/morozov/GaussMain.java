package ru.courses.morozov;

/**
 * Created by Veyron on 08.11.2015.
 */
public class GaussMain {

    public static void main(String[] args) {
        double[][] matrix1 = {{7, 3, 1}, {4, 1, 2}, {0, 0, 0}};
        double[][] matrix2 = {{4, -3, 2, -1}, {3, -2, 1, -3}, {5, -3, 1, -8}};
        double[][] matrix3 = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[][] matrix4 = {{2, 5, 4, 1}, {1, 3, 2, 1}, {2, 10, 9, 7}, {3, 8, 9, 2}};
        double[] vector1 = {1, 7, 0};
        double[] vector2 = {8, 7, 1};
        double[] vector3 = {-1, 13, 9};
        double[] vector4 = {20, 11, 40, 37};

        Gauss gauss1 = new Gauss(new Matrix(matrix1), new Vector(3, vector1));
        Gauss gauss2 = new Gauss(new Matrix(matrix2), new Vector(3, vector2));
        Gauss gauss3 = new Gauss(new Matrix(matrix3), new Vector(3, vector3));
        Gauss gauss4 = new Gauss(new Matrix(matrix4), new Vector(4, vector4));

        System.out.println(gauss1.gauss());
        System.out.println(gauss2.gauss());
        System.out.println(gauss3.gauss());
        System.out.println(gauss4.gauss());
    }
}
