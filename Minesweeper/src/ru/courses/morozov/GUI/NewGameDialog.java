package ru.courses.morozov.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewGameDialog {
    private final int BORDER = 10;

    private int countOfColumns;
    private int countOfRows;
    private int countOfMines;

    private JFrame mainFrame;
    private JDialog newGameFrame;
    private JPanel newGamePanel;
    private JTextField countOfRowsField;
    private JTextField countOfColumnsField;
    private JTextField countOfMinesField;

    private boolean correct;

    public NewGameDialog(JFrame mainFrame, int startCountOfColumns, int startCountOfRows, int startCountOfMines) {
        this.mainFrame = mainFrame;
        newGameFrame = new JDialog(mainFrame);
        newGamePanel = new JPanel(new GridLayout(4, 2, BORDER, BORDER));
        countOfRowsField = new JTextField(Integer.toString(startCountOfRows));
        countOfColumnsField = new JTextField(Integer.toString(startCountOfColumns));
        countOfMinesField = new JTextField(Integer.toString(startCountOfMines));
    }

    public void createDialog() {
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
        JButton buttonOk = new JButton("ОК");
        buttonOk.addActionListener(e -> {
            if (countOfColumnsField.getText().equals("") || countOfRowsField.getText().equals("") ||
                    countOfMinesField.getText().equals("")) {
                JOptionPane.showMessageDialog(mainFrame, "Введите значения", "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isNumber(countOfColumnsField.getText()) || !isNumber(countOfRowsField.getText())
                    || !isNumber(countOfMinesField.getText())) {
                JOptionPane.showMessageDialog(mainFrame, "Введите числовые значения", "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            int tmpRows = Integer.valueOf(countOfRowsField.getText());
            int tmpColumns = Integer.valueOf(countOfColumnsField.getText());
            int tmpMines = Integer.valueOf(countOfMinesField.getText());
            final int MAX_PERCENT_OCCUPANCY_OF_GRID = 85;
            int maxFeasibleCountOfMines = (tmpColumns * tmpRows * MAX_PERCENT_OCCUPANCY_OF_GRID) / 100;
            final int MIN_COUNT_OF_ROWS = 9;
            final int MAX_COUNT_OF_ROWS = 24;
            final int MIN_COUNT_OF_COLUMNS = 9;
            final int MAX_COUNT_OF_COLUMNS = 30;
            final int MIN_COUNT_OF_MINES = 10;
            final int MAX_COUNT_OF_MINES = 668;
            if (tmpRows < MIN_COUNT_OF_ROWS || tmpRows > MAX_COUNT_OF_ROWS) {
                JOptionPane.showMessageDialog(mainFrame, String.format("Высота поля должна быть в пределах от %d до %d",
                        MIN_COUNT_OF_ROWS, MAX_COUNT_OF_ROWS), "Введите значения", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tmpColumns < MIN_COUNT_OF_COLUMNS || tmpColumns > MAX_COUNT_OF_COLUMNS) {
                JOptionPane.showMessageDialog(mainFrame, String.format("Ширина поля должна быть в пределах от %d до %d",
                        MIN_COUNT_OF_COLUMNS, MAX_COUNT_OF_COLUMNS), "Введите значения", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tmpMines < MIN_COUNT_OF_MINES || tmpMines > MAX_COUNT_OF_MINES) {
                JOptionPane.showMessageDialog(mainFrame, String.format("Число мин должно быть в пределах от %d до %d",
                        MIN_COUNT_OF_MINES, MAX_COUNT_OF_MINES), "Введите значения", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tmpMines > maxFeasibleCountOfMines) {
                JOptionPane.showMessageDialog(mainFrame, String.format("Число мин не должно превышать %d в " +
                                "зависимости от текущих значений ширины и высоты", maxFeasibleCountOfMines), "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            countOfColumns = Integer.valueOf(countOfColumnsField.getText());
            countOfRows = Integer.valueOf(countOfRowsField.getText());
            countOfMines = Integer.valueOf(countOfMinesField.getText());
            correct = true;
            newGameFrame.dispose();
        });
        newGamePanel.add(buttonOk);
    }

    private void createCancelButton() {
        JButton buttonCancel = new JButton("Отмена");
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

    private boolean isNumber(String string) {
        return string.matches("^\\d*$");
    }
}
