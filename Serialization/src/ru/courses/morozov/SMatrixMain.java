package ru.courses.morozov;

import java.io.*;

public class SMatrixMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SymmetricMatrix symmetricMatrix = new SymmetricMatrix(5);
        symmetricMatrix.fill();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("Serialization/src/ru/courses/morozov/file2.bin"))) {
            outputStream.writeObject(symmetricMatrix);
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("Serialization/src/ru/courses/morozov/file2.bin"))) {
            SymmetricMatrix symmetricMatrix1 = (SymmetricMatrix) inputStream.readObject();
            System.out.println(symmetricMatrix1);
        }
    }
}
