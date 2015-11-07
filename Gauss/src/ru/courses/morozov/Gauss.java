package ru.courses.morozov;

public class Gauss {
    private Matrix matrix;
    private Vector vector;

    public Gauss(Matrix matrix, Vector vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    private static Matrix augmentedMatrix(Matrix matrix, Vector vector) {
        Matrix augmentedMatrix = new Matrix(matrix.getCountOfStrings(), matrix.getCountOfColumns() + 1);
        for (int i = 0; i < matrix.getCountOfStrings(); ++i) {
            augmentedMatrix.setString(i, augmentedVector(matrix, vector, i));
        }
        return augmentedMatrix;
    }

    private static Vector augmentedVector(Matrix matrix, Vector vector, int index) {
        Vector auxiliaryVector = new Vector(matrix.getCountOfColumns() + 1);
        for (int i = 0; i < matrix.getCountOfColumns(); ++i) {
            auxiliaryVector.setVectorComponent(i, matrix.getString(index).getVectorComponent(i));
        }
        auxiliaryVector.setVectorComponent(auxiliaryVector.getSize() - 1, vector.getVectorComponent(index));
        return auxiliaryVector;
    }

    private static Matrix directFlow(Matrix inputMatrix) {
        int string = 0;
        int column = 0;
        while (string < inputMatrix.getCountOfStrings() && column < inputMatrix.getCountOfColumns()) {
            if (inputMatrix.getString(string).getVectorComponent(column) == 0) {
                setNonZeroComponent(inputMatrix, string, column);
            }
            if (inputMatrix.getString(string).getVectorComponent(column) == 0) {
                ++column;
                continue;
            }
            inputMatrix.setString(string, inputMatrix.getString(string).multiplyByScalar
                    (1 / inputMatrix.getString(string).getVectorComponent(column)));
            for (int i = string + 1; i < inputMatrix.getCountOfStrings(); ++i) {
                inputMatrix.setString
                        (i, Vector.subtraction(inputMatrix.getString(i), inputMatrix.getString(string).
                                multiplyByScalar(inputMatrix.getString(i).getVectorComponent(column))));
            }
            ++string;
            ++column;
        }
        return inputMatrix;
    }

    private static Matrix reversal(Matrix matrix) {
        for (int i = matrix.getCountOfStrings() - 1; i > 0; --i) {
            for (int j = i - 1; j >= 0; --j) {
                matrix.setString(j, matrix.getString(j).subtraction(matrix.getString(i).
                        multiplyByScalar(matrix.getString(j).getVectorComponent(i))));
            }
        }
        return matrix;
    }

    public ResultGauss gauss(Matrix matrix, Vector vector) {
        Matrix operatingMatrix = new Matrix(augmentedMatrix(matrix, vector));
        //проверка на совместность системы

        if (getRank(directFlow(matrix)) != getRank(directFlow(operatingMatrix))) {
            return new ResultGauss(-1);
        }
        if (getRank(directFlow(matrix)) != matrix.getCountOfColumns()) {
            return new ResultGauss(1);
        }
        directFlow(operatingMatrix);
        reversal(operatingMatrix);
        return new ResultGauss(0, (operatingMatrix.getColumn(operatingMatrix.getCountOfColumns() - 1)));
    }

    private static int getRank(Matrix matrix) {
        int rank = 0;
        for (int i = 0; i < matrix.getCountOfStrings(); ++i) {
            if (matrix.getString(i).equals(new Vector(matrix.getCountOfColumns()))) {
                continue;
            }
            ++rank;
        }
        return rank;
    }

    public static Matrix setNonZeroComponent(Matrix matrix, int string, int column) {
        //проверка, что в заданном столбце есть не нулевой элемент.
        for (int i = string; i < matrix.getCountOfStrings(); ++i) {
            if (matrix.getString(i).getVectorComponent(column) != 0) {
                //замена строки с нулевым компонентом.
                for (int j = string; j < matrix.getCountOfStrings(); ++j) {
                    if (matrix.getString(j).getVectorComponent(column) == 0) {
                        Vector auxiliaryVector = new Vector(matrix.getString(j));
                        for (int k = j; k < matrix.getCountOfStrings() - 1; ++k) {
                            matrix.setString(k, matrix.getString(k + 1));
                        }
                        matrix.setString(matrix.getCountOfStrings() - 1, auxiliaryVector);

                        //проверка, что все элементы начиная с j-ого равны нулю. (чтобы выйти из бесконечного цикла)
                        Vector zeroVector = new Vector(matrix.getCountOfStrings() - j);
                        for (int l = j; l < matrix.getCountOfStrings(); ++l) {
                            zeroVector.setVectorComponent(l - j, matrix.getString(l).getVectorComponent(column));
                        }
                        if (zeroVector.equals(new Vector(zeroVector.getSize()))) {
                            break;
                        }
                        --j;
                    }
                }
                break;
            }
        }
        return matrix;
    }
}
