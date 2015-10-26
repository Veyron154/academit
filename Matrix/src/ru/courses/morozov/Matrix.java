package ru.courses.morozov;


/**
 * Created by Veyron on 25.10.2015.
 */
public class Matrix {
    private double[][] matrixComponents;

    public Matrix(int countOfStrings, int countOfColumns) {
        if (countOfStrings <= 0 || countOfColumns <= 0){
            throw new IllegalArgumentException("Размер матрицы должен быть больше нуля");
        }
        this.matrixComponents = new double[countOfStrings][countOfColumns];
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

    public int getCountOfStrings(){
        return this.matrixComponents.length;
    }

    public int getCountOfColunms(){
        return this.matrixComponents[0].length;
    }

    public Vector getString(int index){
        return new Vector(this.matrixComponents[0].length, this.matrixComponents[index]);
    }
}
