package ru.courses.morozov.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private String pathToLogFile;

    public FileLogger(String pathToLogFile) {
        this.pathToLogFile = pathToLogFile;
    }

    @Override
    public void write(String loggingString) {
        File logFile = new File(pathToLogFile);
        try {
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(loggingString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
