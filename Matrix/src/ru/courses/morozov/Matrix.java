package ru.courses.morozov;


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
        this.matrixComponents = new double[arrayOfVectors.length][arrayOfVectors[0].getSize()];
        for(int i = 0; i < arrayOfVectors.length; ++i){
            for (int j = 0; j < arrayOfVectors[0].getSize(); ++j){
                this.matrixComponents[i][j] = arrayOfVectors[i].getVectorComponent(j);
            }
        }
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
