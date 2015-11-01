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
        builder.append("{");
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

    public Matrix multiplyByScalar(int scalar) {
        for (int i = 0; i < this.matrixComponents.length; ++i) {
            for (int j = 0; j < this.matrixComponents[0].length; ++j) {
                this.matrixComponents[i][j] *= scalar;
            }
        }
        return this;
    }

    private static double getDeterminantBinaryMatrix(Matrix binaryMatrix) {
        return binaryMatrix.matrixComponents[0][0] * binaryMatrix.matrixComponents[1][1] -
                binaryMatrix.matrixComponents[1][0] * binaryMatrix.matrixComponents[0][1];
    }

    private static Matrix getCofactor(Matrix inputMatrix, int index) {
        double[][] auxiliaryMatrix = new double[inputMatrix.getCountOfStrings() - 1]
                [inputMatrix.getCountOfColumns() - 1];
        int stringIndex = 0;
        for (int i = 1; i < inputMatrix.getCountOfStrings(); ++i) {
            int columnIndex = 0;
            for (int j = 0; j < inputMatrix.getCountOfColumns(); ++j) {
                if (j == index) {
                    continue;
                }
                auxiliaryMatrix[stringIndex][columnIndex] = inputMatrix.matrixComponents[i][j];
                ++columnIndex;
            }
            ++stringIndex;
        }
        return new Matrix(auxiliaryMatrix);
    }

    public double getDeterminant() {
        if (this.getCountOfColumns() != this.getCountOfStrings()) {
            throw new IllegalArgumentException("Определитель высчитывается только у квадратной матрицы");
        }
        if (this.getCountOfColumns() == 2) {
            return getDeterminantBinaryMatrix(this);
        }
        double determinant = 0;
        for (int i = 0; i < this.getCountOfColumns(); ++i) {
            if (i % 2 == 0) {
                determinant += this.matrixComponents[0][i] * Matrix.getCofactor(this, i).getDeterminant();
            } else {
                determinant -= this.matrixComponents[0][i] * Matrix.getCofactor(this, i).getDeterminant();
            }
        }
        return determinant;
    }

    public Vector multiplyByVector(Vector inputVector) {
        if (inputVector.getSize() != this.getCountOfColumns()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна числу столбцов матрицы");
        }
        double[] auxiliaryVector = new double[this.getCountOfStrings()];
        for (int i = 0; i < this.getCountOfStrings(); ++i) {
            for (int j = 0; j < this.getCountOfColumns(); ++j) {
                auxiliaryVector[i] += this.matrixComponents[i][j] * inputVector.getVectorComponent(j);
            }
        }
        return new Vector(auxiliaryVector.length, auxiliaryVector);
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        double[][] auxiliaryMatrix1 = Matrix.extentionMatrix(Math.max(matrix1.getCountOfStrings(),
                matrix2.getCountOfStrings()), Math.max(matrix1.getCountOfColumns(),
                matrix2.getCountOfColumns()), matrix1.matrixComponents).matrixComponents;
        double[][] auxiliaryMatrix2 = Matrix.extentionMatrix(Math.max(matrix1.getCountOfStrings(),
                matrix2.getCountOfStrings()), Math.max(matrix1.getCountOfColumns(),
                matrix2.getCountOfColumns()), matrix2.matrixComponents).matrixComponents;
        for (int i = 0; i < auxiliaryMatrix1.length; ++i) {
            for (int j = 0; j < auxiliaryMatrix1[0].length; ++j) {
                auxiliaryMatrix1[i][j] += auxiliaryMatrix2[i][j];
            }
        }
        return new Matrix(auxiliaryMatrix1);
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        double[][] auxiliaryMatrix1 = Matrix.extentionMatrix(Math.max(matrix1.getCountOfStrings(),
                matrix2.getCountOfStrings()), Math.max(matrix1.getCountOfColumns(),
                matrix2.getCountOfColumns()), matrix1.matrixComponents).matrixComponents;
        double[][] auxiliaryMatrix2 = Matrix.extentionMatrix(Math.max(matrix1.getCountOfStrings(),
                matrix2.getCountOfStrings()), Math.max(matrix1.getCountOfColumns(),
                matrix2.getCountOfColumns()), matrix2.matrixComponents).matrixComponents;
        for (int i = 0; i < auxiliaryMatrix1.length; ++i) {
            for (int j = 0; j < auxiliaryMatrix1[0].length; ++j) {
                auxiliaryMatrix1[i][j] -= auxiliaryMatrix2[i][j];
            }
        }
        return new Matrix(auxiliaryMatrix1);
    }

    public Matrix addition(Matrix inputMatrix) {
        this.matrixComponents = Matrix.addition(this, inputMatrix).matrixComponents;
        return this;
    }

    public Matrix subtraction(Matrix inputMatrix) {
        this.matrixComponents = Matrix.subtraction(this, inputMatrix).matrixComponents;
        return this;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getCountOfColumns() != matrix2.getCountOfStrings()) {
            throw new IllegalArgumentException
                    ("Число столбцов в первой матрице должно быть равно числу строк во второй");
        }
        double[][] auxiliaryMatrix = new double[matrix1.getCountOfStrings()][matrix2.getCountOfColumns()];
        for (int i = 0; i < auxiliaryMatrix.length; ++i) {
            for (int j = 0; j < auxiliaryMatrix[0].length; ++j) {
                for (int k = 0; k < matrix1.getCountOfColumns(); ++k) {
                    auxiliaryMatrix[i][j] += matrix1.matrixComponents[i][k] * matrix2.matrixComponents[k][j];
                }
            }
        }
        return new Matrix(auxiliaryMatrix);
    }

    private static Matrix extentionMatrix(int countOfString, int countOfColumns, double[][] matrixComponents) {
        double[][] auxiliaryArray = new double[countOfString][countOfColumns];
        for (int i = 0; i < matrixComponents.length; ++i) {
            System.arraycopy(matrixComponents[i], 0, auxiliaryArray[i], 0, matrixComponents[0].length);
        }
        return new Matrix(auxiliaryArray);
    }
}
