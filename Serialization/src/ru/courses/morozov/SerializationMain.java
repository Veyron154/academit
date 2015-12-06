package ru.courses.morozov;

import java.io.*;

public class SerializationMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Rectangle rectangle = new Rectangle(100, 200);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("Serialization/src/ru/courses/morozov/file.bin"))) {
            outputStream.writeObject(rectangle);
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("Serialization/src/ru/courses/morozov/file.bin"))) {
            Rectangle rectangle2 = (Rectangle) inputStream.readObject();
            System.out.println(rectangle2.getHeight() * rectangle2.getWidth());
        }
    }
}
