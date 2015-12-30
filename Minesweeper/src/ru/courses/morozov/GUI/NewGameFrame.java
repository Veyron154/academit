package ru.courses.morozov.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewGameFrame {
    private int countOfColumns;
    private int countOfRows;
    private int countOfMines;
    private JFrame mainFrame;
    private final int BORDER = 10;
    private JPanel newGamePanel = new JPanel(new GridLayout(4, 2, BORDER, BORDER));
    private JTextField countOfRowsField = new JTextField();
    private JTextField countOfColumnsField = new JTextField();
    private JTextField countOfMinesField = new JTextField();
    private JDialog newGameFrame;
    private JButton buttonOk = new JButton("ОК");
    private JButton buttonCancel = new JButton("Отмена");
    private boolean correct = false;

    public NewGameFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void createFrame() {
        newGameFrame = new JDialog(mainFrame);
        newGameFrame.setModal(true);
        newGameFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newGameFrame.setTitle("Новая игра");
        createPanel();
        newGameFrame.add(newGamePanel);
        newGameFrame.pack();
        newGameFrame.setLocationRelativeTo(mainFrame);
        newGameFrame.setVisible(true);
    }

    public int getCountOfColumns() {
        return this.countOfColumns;
    }

    public int getCountOfRows() {
        return this.countOfRows;
    }

    public int getCountOfMines() {
        return this.countOfMines;
    }

    private void createOkButton() {
        buttonOk.addActionListener(e -> {
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
                if (tmpRows < 9 || tmpRows > 24) {
                    JOptionPane.showMessageDialog(mainFrame, "Высота поля должна быть в пределах от 9 до 24",
                            "Введите значения", JOptionPane.ERROR_MESSAGE);
                } else if (tmpMines < 10 || tmpMines > 668) {
                    JOptionPane.showMessageDialog(mainFrame, "Число мин должно быть в пределах от 10 до 668",
                            "Введите значения", JOptionPane.ERROR_MESSAGE);
                } else if (tmpColumns < 9 || tmpColumns > 30) {
                    JOptionPane.showMessageDialog(mainFrame, "Ширина поля должна быть в пределах от 9 до 30",
                            "Введите значения", JOptionPane.ERROR_MESSAGE);
                } else if (tmpMines > tmpColumns * tmpRows) {
                    JOptionPane.showMessageDialog(mainFrame, "Число мин не должно превышать число ячеек поля",
                            "Введите значения", JOptionPane.ERROR_MESSAGE);
                } else {
                    countOfColumns = Integer.valueOf(countOfColumnsField.getText());
                    countOfRows = Integer.valueOf(countOfRowsField.getText());
                    countOfMines = Integer.valueOf(countOfMinesField.getText());
                    correct = true;
                    newGameFrame.dispose();
                }
            }
        });
        newGamePanel.add(buttonOk);
    }

    private void createCancelButton() {
        buttonCancel.addActionListener(e -> newGameFrame.dispose());
        newGamePanel.add(buttonCancel);
    }

    public boolean isCorrect() {
        return this.correct;
    }

    private void createPanel() {
        JLabel countOfRowsLabel = new JLabel("Высота (9 - 24):");
        JLabel countOfColumnsLabel = new JLabel("Ширина (9 - 30):");
        JLabel countOfMinesLabel = new JLabel("Число мин (10 - 668):");
        newGamePanel.add(countOfRowsLabel);
        newGamePanel.add(countOfRowsField);
        newGamePanel.add(countOfColumnsLabel);
        newGamePanel.add(countOfColumnsField);
        newGamePanel.add(countOfMinesLabel);
        newGamePanel.add(countOfMinesField);
        createOkButton();
        createCancelButton();
        newGamePanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
    }
}
