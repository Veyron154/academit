package ru.courses.morozov;

import java.io.*;

public class IOMain {
    public static void main(String[] args) throws IOException {
        copying();
        write();
    }

    public static void copying() throws IOException {
        byte[] bytes = new byte[100];
        try (BufferedInputStream inputStream = new BufferedInputStream(
                new FileInputStream("IO/src/ru/courses/morozov/file1.txt"))) {
            int read;
            int off = 0;
            while ((read = inputStream.read(bytes, off, bytes.length - off)) != -1) {
                off += read;
            }
        }
        try (BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream("IO/src/ru/courses/morozov/file2.txt"))) {
            outputStream.write(bytes);
        }
    }


    public static void write() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("IO/src/ru/courses/morozov/file3.txt")) {
            int number = 156;
            for (int i = 1; i <= 100; ++i) {
                writer.println("Строка " + i);
            }
            writer.printf("Эта строка не %d%n", number);
            writer.print("И эта строка не " + number);
        }
    }
}
