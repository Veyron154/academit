package ru.courses.morozov;

public class Matrix {
    private double[][] matrixComponents;

    public Matrix(int countOfStrings, int countOfColumns) {
        if (countOfStrings <= 0 || countOfColumns <= 0) {
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
        for (int i = 0; i < arrayOfVectors.length; ++i) {
            for (int j = 0; j < arrayOfVectors[0].getSize(); ++j) {
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

    public int getCountOfStrings() {
        return this.matrixComponents.length;
    }

    public int getCountOfColumns() {
        return this.matrixComponents[0].length;
    }

    public Vector getString(int index) {
        return new Vector(this.matrixComponents[0].length, this.matrixComponents[index]);
    }

    public void setString(int index, Vector newString) {
        for (int i = 0; i < this.matrixComponents[0].length; ++i) {
            this.matrixComponents[index][i] = newString.getVectorComponent(i);
        }
    }

    public Vector getColumn(int index) {
        double[] auxiliaryVector = new double[this.getCountOfStrings()];
        for (int i = 0; i < this.getCountOfStrings(); ++i) {
            auxiliaryVector[i] = this.matrixComponents[i][index];
        }
        return new Vector(this.getCountOfStrings(), auxiliaryVector);
    }

    public Matrix transpose() {
        Matrix auxiliaryMatrix = new Matrix(this.getCountOfColumns(), this.getCountOfStrings());
        for (int i = 0; i < this.getCountOfColumns(); ++i) {
            auxiliaryMatrix.setString(i, this.getColumn(i));
        }
        this.matrixComponents = auxiliaryMatrix.matrixComponents;
        return this;
    }

    public Matrix multiplyByScalar(int scalar){
        for (int i = 0; i < this.matrixComponents.length; ++i){
            for (int j = 0; j < this.matrixComponents[0].length; ++j){
                this.matrixComponents[i][j] *= scalar;
            }
        }
        return this;
    }

    private static double getDeterminantBinaryMatrix(Matrix binaryMatrix){
        return binaryMatrix.matrixComponents[0][0] * binaryMatrix.matrixComponents[1][1] -
                binaryMatrix.matrixComponents[1][0] * binaryMatrix.matrixComponents[0][1];
    }


}
