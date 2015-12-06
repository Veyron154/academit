package ru.courses.morozov;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class SymmetricMatrix implements Serializable {
    private int[][] matrixElements;

    public SymmetricMatrix(int size) {
        matrixElements = new int[size][size];
    }

    public void fill() {
        for (int i = 0; i < matrixElements.length; ++i) {
            for (int j = 0; j + i < matrixElements[0].length; ++j) {
                matrixElements[i][j + i] = j;
                matrixElements[j + i][i] = j;
            }
        }
    }

    public String toString() {
        return Arrays.deepToString(matrixElements);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(matrixElements.length);
        for (int i = 0; i < matrixElements.length; ++i) {
            for (int j = i; j < matrixElements.length; ++j) {
                out.writeInt(matrixElements[i][j]);
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        matrixElements = new int[size][size];
        for (int i = 0; i < matrixElements.length; ++i) {
            for (int j = i; j < matrixElements.length; ++j) {
                int element = in.readInt();
                matrixElements[i][j] = element;
                matrixElements[j][i] = element;
            }
        }
    }
}
