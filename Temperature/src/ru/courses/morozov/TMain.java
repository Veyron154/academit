package ru.courses.morozov;

import javax.swing.*;

public class TMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Temperature();
            }
        });
    }
}
