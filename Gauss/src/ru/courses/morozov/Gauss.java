package ru.courses.morozov;

public class Gauss {
    private Matrix matrix;
    private Vector vector;

    public Gauss(Matrix matrix, Vector vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    private Matrix augmentedMatrix() {
        Matrix augmentedMatrix = new Matrix(matrix.getCountOfRows(), matrix.getCountOfColumns() + 1);
        for (int i = 0; i < matrix.getCountOfRows(); ++i) {
            augmentedMatrix.setRow(i, matrix.getRow(i));
        }
        augmentedMatrix.setColumn(vector, augmentedMatrix.getCountOfColumns() - 1);
        return augmentedMatrix;
    }

    private static Matrix directFlow(Matrix inputMatrix) {
        int string = 0;
        int column = 0;
        while (string < inputMatrix.getCountOfRows() && column < inputMatrix.getCountOfColumns()) {
            if (inputMatrix.getRow(string).getVectorComponent(column) == 0) {
                setNonZeroComponent(inputMatrix, string, column);
            }
            if (inputMatrix.getRow(string).getVectorComponent(column) == 0) {
                ++column;
                continue;
            }
            inputMatrix.setRow(string, inputMatrix.getRow(string).multiplyByScalar
                    (1 / inputMatrix.getRow(string).getVectorComponent(column)));
            for (int i = string + 1; i < inputMatrix.getCountOfRows(); ++i) {
                inputMatrix.setRow
                        (i, Vector.subtraction(inputMatrix.getRow(i), inputMatrix.getRow(string).
                                multiplyByScalar(inputMatrix.getRow(i).getVectorComponent(column))));
            }
            ++string;
            ++column;
        }
        return inputMatrix;
    }

    private static Matrix reversal(Matrix matrix) {
        for (int i = matrix.getCountOfRows() - 1; i > 0; --i) {
            for (int j = i - 1; j >= 0; --j) {
                matrix.setRow(j, matrix.getRow(j).subtraction(matrix.getRow(i).
                        multiplyByScalar(matrix.getRow(j).getVectorComponent(i))));
            }
        }
        return matrix;
    }

    public ResultGauss gauss() {
        Matrix operatingMatrix = new Matrix(this.augmentedMatrix());
        //проверка на совместность системы

        Matrix directFlowMatrix = new Matrix(directFlow(matrix));
        Matrix directFlowOperatingMatrix = new Matrix(directFlow(operatingMatrix));

        int rankMatrix = getRank(directFlowMatrix);
        int rankOperatingMatrix = getRank(directFlowOperatingMatrix);

        if (rankMatrix != rankOperatingMatrix) {
            return new ResultGauss(-1);
        }
        if (rankMatrix != matrix.getCountOfColumns()) {
            return new ResultGauss(1);
        }
        reversal(directFlowOperatingMatrix);
        return new ResultGauss(0, (directFlowOperatingMatrix.getColumn(operatingMatrix.getCountOfColumns() - 1)));
    }

    private static int getRank(Matrix matrix) {
        int rank = 0;
        Vector zeroVector = new Vector(matrix.getCountOfColumns());
        for (int i = 0; i < matrix.getCountOfRows(); ++i) {
            if (matrix.getRow(i).equals(zeroVector)) {
                continue;
            }
            ++rank;
        }
        return rank;
    }

    public static Matrix setNonZeroComponent(Matrix matrix, int string, int column) {
        //проверка, что в заданном столбце есть не нулевой элемент.
        for (int i = string; i < matrix.getCountOfRows(); ++i) {
            if (matrix.getRow(i).getVectorComponent(column) != 0) {
                //замена строки с нулевым компонентом.
                for (int j = string; j < matrix.getCountOfRows(); ++j) {
                    if (matrix.getRow(j).getVectorComponent(column) == 0) {
                        Vector auxiliaryVector = new Vector(matrix.getRow(j));
                        for (int k = j; k < matrix.getCountOfRows() - 1; ++k) {
                            matrix.setRow(k, matrix.getRow(k + 1));
                        }
                        matrix.setRow(matrix.getCountOfRows() - 1, auxiliaryVector);

                        //проверка, что все элементы начиная с j-ого равны нулю. (чтобы выйти из бесконечного цикла)
                        Vector zeroVector = new Vector(matrix.getCountOfRows() - j);
                        for (int l = j; l < matrix.getCountOfRows(); ++l) {
                            zeroVector.setVectorComponent(l - j, matrix.getRow(l).getVectorComponent(column));
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
