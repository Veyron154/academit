package ru.courses.morozov;

import ru.courses.morozov.GUI.MineFrame;
import ru.courses.morozov.text.MineText;

import java.io.IOException;


public class MineMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MineFrame mineFrame = new MineFrame();
        mineFrame.createFrame();
        //MineText mineText = new MineText();
        //mineText.run();
    }
}