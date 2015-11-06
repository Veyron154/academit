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
            matrix.setString(i, matrix.getString(i).multiplyByScalar(1 / matrix.getString(i).getVectorComponent(i)));
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
        double[][] array1 = {{0, 2, -5}, {0, -1, 3}, {0, 2, -1}, {4, 5, 9}};
        double[] array2 = {-1, 13, 9};

        Matrix matrix = new Matrix(array1);
        Vector vector = new Vector(3, array2);

        //System.out.println(getRang(directFlow(matrix)));
        System.out.println(setNonZeroComponent(matrix, 0, 0));
    }

    private static Vector gauss(Matrix matrix, Vector vector) {
        Matrix operatingMatrix = new Matrix(augmentedMatrix(matrix, vector));
        directFlow(operatingMatrix);
        reversal(operatingMatrix);
        return new Vector(operatingMatrix.getColumn(operatingMatrix.getCountOfColumns() - 1));
    }

    private static int getRang(Matrix matrix){
        int rang = 0;
        for(int i = 0; i < matrix.getCountOfStrings(); ++i){
            if (matrix.getString(i).equals(new Vector(matrix.getCountOfColumns()))){
                continue;
            }
            ++rang;
        }
        return rang;
    }

    public static Matrix setNonZeroComponent(Matrix matrix, int string, int column){
        //проверка, что в заданном столбце есть не нулевой элемент.
        for(int i = string; i < matrix.getCountOfStrings(); ++i){
            if(matrix.getString(i).getVectorComponent(column) != 0){
                //замена строки с нулевым компонентом.
                for(int j = string; j < matrix.getCountOfStrings(); ++j){
                    if(matrix.getString(j).getVectorComponent(column) == 0) {
                        Vector auxiliaryVector = new Vector(matrix.getString(j));
                        for (int k = j; k < matrix.getCountOfStrings() - 1; ++k) {
                            matrix.setString(k, matrix.getString(k + 1));
                        }
                        matrix.setString(matrix.getCountOfStrings() - 1, auxiliaryVector);

                        //проверка, что все элементы начиная с j-ого равны нулю. (чтобы выйти из бесконечного цикла)
                        Vector zeroVector = new Vector(matrix.getCountOfStrings() - j);
                        for(int l = j; l < matrix.getCountOfStrings(); ++l){
                            zeroVector.setVectorComponent(l - j, matrix.getString(l).getVectorComponent(column));
                        }
                        if(zeroVector.equals(new Vector(zeroVector.getSize()))){
                            break;
                        }
                        --j;
                    }
                }
                break;
            }
        }
        System.out.println(matrix);
        return matrix;
    }
}
