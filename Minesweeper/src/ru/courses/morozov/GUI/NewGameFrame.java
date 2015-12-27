package ru.courses.morozov.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewGameFrame {
    private int countOfColumns;
    private int countOfRows;
    private int countOfMines;

    public NewGameFrame(JFrame mainFrame){
        final int BORDER = 10;
        JPanel newGamePanel = new JPanel(new GridLayout(3, 2, BORDER, BORDER));
        JLabel countOfRowsLabel = new JLabel("Высота (9 - 24):");
        JLabel countOfColumnsLabel = new JLabel("Ширина (9 - 30):");
        JLabel countOfMinesLabel = new JLabel("Число мин (10 - 668):");
        JTextField countOfRowsField = new JTextField();
        JTextField countOfColumnsField = new JTextField();
        JTextField countOfMinesField = new JTextField();
        newGamePanel.add(countOfRowsLabel);
        newGamePanel.add(countOfRowsField);
        newGamePanel.add(countOfColumnsLabel);
        newGamePanel.add(countOfColumnsField);
        newGamePanel.add(countOfMinesLabel);
        newGamePanel.add(countOfMinesField);
        newGamePanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        int reply = JOptionPane.showConfirmDialog(mainFrame, newGamePanel, "Новая игра", JOptionPane.OK_CANCEL_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            if (countOfColumnsField.getText().equals("") || countOfRowsField.getText().equals("") ||
                    countOfMinesField.getText().equals("")) {
                JOptionPane.showMessageDialog(mainFrame, "Введите значения", "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!countOfColumnsField.getText().matches("^\\d*$") || !countOfRowsField.getText().matches("^\\d*$")
                    || !countOfMinesField.getText().matches("^\\d*$")) {
                JOptionPane.showMessageDialog(mainFrame, "Введите числовые значения", "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int tmpRows = Integer.valueOf(countOfRowsField.getText());
                int tmpColumns = Integer.valueOf(countOfColumnsField.getText());
                int tmpMines = Integer.valueOf(countOfMinesField.getText());
                if (tmpRows < 9 || tmpRows > 24 || tmpColumns < 9 || tmpColumns > 30 || tmpMines < 10 ||
                        tmpMines > 668) {
                    JOptionPane.showInputDialog(mainFrame, "Введите корректные значения", "Введите значения",
                            JOptionPane.ERROR_MESSAGE);
                } else if (tmpMines > tmpColumns * tmpRows) {
                    JOptionPane.showMessageDialog(mainFrame, "Число мин не должно превышать число ячеек поля",
                            "Введите значения", JOptionPane.ERROR_MESSAGE);
                } else {
                    countOfColumns = Integer.valueOf(countOfColumnsField.getText());
                    countOfRows = Integer.valueOf(countOfRowsField.getText());
                    countOfMines = Integer.valueOf(countOfMinesField.getText());
                }
            }
        }
    }

    public int getCountOfColumns(){
        return this.countOfColumns;
    }

    public int getCountOfRows(){
        return this.countOfRows;
    }

    public int getCountOfMines(){
        return this.countOfMines;
    }
}
