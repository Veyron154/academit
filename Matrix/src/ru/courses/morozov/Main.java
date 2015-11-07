package ru.courses.morozov;

/**
 * Created by Veyron on 25.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        double[][] array1 = {{4, 2, 7},{9, 0, 8},{8, 10, 2}};
        double[][] array2 = {{3, 1, 6},{-3, 4, 12}};
        double[][] array3 = {{4, 2, 7},{9, 0, 8},{8, 10, 2}};
        double[] vector = {1, 2};
        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);
        Matrix matrix3 = new Matrix(array3);

        Matrix matrix4 = new Matrix(12, 12);
/*
        System.out.println(matrix1.getColumn(1));
        System.out.println(matrix2.getRow(0));
        System.out.println(matrix1.getDeterminant());
        System.out.println(Matrix.subtraction(matrix1, matrix2));
        System.out.println(matrix1.equals(matrix3));
        System.out.println(matrix1.hashCode() == matrix3.hashCode());
        System.out.println(matrix2.transpose());
        System.out.println(Matrix.multiply(matrix1, matrix2));
        System.out.println(matrix2.multiplyByVector(vector1));
        */

        System.out.println(matrix4.getDeterminant());
    }

}
