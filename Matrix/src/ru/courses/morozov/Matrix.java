package ru.courses.morozov;

import java.util.Vector;

/**
 * Created by Veyron on 25.10.2015.
 */
public class Matrix {
    double[][] matrixComponents;

    public Matrix(int n, int m) {
        this.matrixComponents = new double[n][m];
    }

    public Matrix(Matrix copiedMatrix) {
        this.matrixComponents = copiedMatrix.matrixComponents;
    }

    public Matrix(double[][] inputArray) {
        this.matrixComponents = inputArray;
    }

    public Matrix(Vector[] arrayOfVectors) {
        this.matrixComponents = new double[arrayOfVectors.length][];
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (double[] matrixComponent : this.matrixComponents) {
            builder.append("{ ");
            for (int j = 0; j < this.matrixComponents[0].length; ++j) {
                builder.append(matrixComponent[j])
                        .append(", ");
            }
            builder.append("}");
            builder.deleteCharAt(builder.lastIndexOf(","));
            builder.append(",");
        }
        builder.append("}");
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }
}
