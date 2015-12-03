package ru.courses.morozov.view;

import ru.courses.morozov.model.DeterminantConverter;
import ru.courses.morozov.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TemperatureGraphic {

    private JLabel inputLabel = new JLabel("Введите температуру: ");
    private JLabel outLabel = new JLabel("Результат: ");
    private JLabel switchLabel1 = new JLabel("Выберите вариант перевода: ");
    private JTextField inputField = new JTextField();
    private JTextField outField = new JTextField();
    private JComboBox<TemperatureScale> comboBox1 = new JComboBox<>(TemperatureScale.values());
    private JComboBox<TemperatureScale> comboBox2 = new JComboBox<>(TemperatureScale.values());
    private JButton swap = new JButton("<=>");
    private JButton convertButton = new JButton("Перевести");
    private static final int STRUT = 12;

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame temperatureFrame = new JFrame();
                temperatureFrame.setTitle("Перевод температур");
                temperatureFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                temperatureFrame.add(createMainBox());

                inputLabel.setPreferredSize(switchLabel1.getPreferredSize());
                outLabel.setPreferredSize(switchLabel1.getPreferredSize());

                swap.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int tmpIndex = comboBox1.getSelectedIndex();
                        comboBox1.setSelectedIndex(comboBox2.getSelectedIndex());
                        comboBox2.setSelectedIndex(tmpIndex);
                    }
                });

                convertButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (inputField.getText().equals("")) {
                            JOptionPane.showMessageDialog(convertButton, "Введите значение", "Введите число",
                                    JOptionPane.ERROR_MESSAGE);
                        } else if (!inputField.getText().matches("^\\-?\\d*\\.?\\d*$")) {
                            JOptionPane.showMessageDialog(convertButton, "Введите числовое значение", "Введите число",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            outField.setText(Double.toString(DeterminantConverter.determine(comboBox1.getItemAt
                                            (comboBox1.getSelectedIndex()), comboBox2.getItemAt(comboBox2.getSelectedIndex()),
                                    Double.valueOf(inputField.getText()))));
                        }
                    }
                });

                temperatureFrame.pack();
                temperatureFrame.setLocationRelativeTo(null);
                temperatureFrame.setMinimumSize(new Dimension(temperatureFrame.getWidth(), temperatureFrame.getHeight()));
                temperatureFrame.setVisible(true);
            }
        });
    }

    private Box createInputBox() {
        Box inputBox = Box.createHorizontalBox();
        inputBox.add(inputLabel);
        inputBox.add(Box.createHorizontalStrut(STRUT));
        inputBox.add(inputField);
        return inputBox;
    }

    private Box createOutBox() {
        Box outBox = Box.createHorizontalBox();
        outField.setEditable(false);
        outBox.add(outLabel);
        outBox.add(Box.createHorizontalStrut(STRUT));
        outBox.add(outField);
        return outBox;
    }

    private Box createSwitchBox() {
        Box switchBox = Box.createHorizontalBox();
        switchBox.add(switchLabel1);
        switchBox.add(Box.createHorizontalStrut(STRUT));
        switchBox.add(comboBox1);
        switchBox.add(Box.createHorizontalStrut(STRUT));
        switchBox.add(swap);
        switchBox.add(Box.createHorizontalStrut(STRUT));
        switchBox.add(comboBox2);
        return switchBox;
    }

    private Box createMainBox() {
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(STRUT, STRUT, STRUT, STRUT));
        mainBox.add(createInputBox());
        mainBox.add(Box.createVerticalStrut(STRUT));
        mainBox.add(createOutBox());
        mainBox.add(Box.createVerticalStrut(STRUT));
        mainBox.add(createSwitchBox());
        mainBox.add(Box.createVerticalStrut(STRUT));
        mainBox.add(convertButton);
        convertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return mainBox;
    }
}
