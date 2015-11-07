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
        int row = 0;
        int column = 0;
        while (row < inputMatrix.getCountOfRows() && column < inputMatrix.getCountOfColumns()) {
            if (inputMatrix.getRow(row).getVectorComponent(column) == 0) {
                setNonZeroComponent(inputMatrix, row, column);
            }
            if (inputMatrix.getRow(row).getVectorComponent(column) == 0) {
                ++column;
                continue;
            }
            inputMatrix.setRow(row, inputMatrix.getRow(row).multiplyByScalar
                    (1 / inputMatrix.getRow(row).getVectorComponent(column)));
            for (int i = row + 1; i < inputMatrix.getCountOfRows(); ++i) {
                inputMatrix.setRow
                        (i, Vector.subtraction(inputMatrix.getRow(i), inputMatrix.getRow(row).
                                multiplyByScalar(inputMatrix.getRow(i).getVectorComponent(column))));
            }
            ++row;
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
            return new ResultGauss(ResultGauss.Code.NO_SOLUTION);
        }
        if (rankMatrix != matrix.getCountOfColumns()) {
            return new ResultGauss(ResultGauss.Code.A_LOT_OF_SOLUTION);
        }
        reversal(directFlowOperatingMatrix);
        return new ResultGauss(directFlowOperatingMatrix.getColumn(operatingMatrix.getCountOfColumns() - 1));
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

    public static Matrix setNonZeroComponent(Matrix matrix, int row, int column) {
        //проверка, что в заданном столбце есть не нулевой элемент.
        for (int i = row; i < matrix.getCountOfRows(); ++i) {
            if (matrix.getRow(i).getVectorComponent(column) != 0) {
                //замена строки с нулевым компонентом.
                for (int j = row; j < matrix.getCountOfRows(); ++j) {
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
