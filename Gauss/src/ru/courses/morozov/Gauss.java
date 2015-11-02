package ru.courses.morozov;

public class Gauss {
    public static Matrix augmentedMatrix(Matrix matrix, Vector vector) {
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

    private static Matrix directFlow(Matrix matrix) {
        for (int i = 0; i < matrix.getCountOfStrings(); ++i) {
            matrix.setString(i, matrix.getString(i).multiplyByScalar
                    (1 / matrix.getString(i).getVectorComponent(i)));
            for (int j = i + 1; j < matrix.getCountOfStrings(); ++j) {
                matrix.setString(j, matrix.getString(j).subtraction(matrix.getString(i).
                        multiplyByScalar(matrix.getString(j).getVectorComponent(i))));
            }
        }
        return matrix;
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

    public static void main(String[] args) {
        double[][] array1 = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[] array2 = {-1, 13, 9};

        Matrix matrix = new Matrix(array1);
        Vector vector = new Vector(3, array2);

        System.out.println(gauss(matrix, vector));
    }

    private static Vector gauss(Matrix matrix, Vector vector) {
        Matrix operatingMatrix = new Matrix(augmentedMatrix(matrix, vector));
        directFlow(operatingMatrix);
        reversal(operatingMatrix);
        return new Vector(operatingMatrix.getColumn(operatingMatrix.getCountOfColumns() - 1));
    }
}
