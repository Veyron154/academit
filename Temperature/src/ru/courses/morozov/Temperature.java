package ru.courses.morozov;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Temperature extends JFrame {

    public Temperature() {
        super("Перевод температур");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Box inputBox = Box.createHorizontalBox();
        JLabel inputLabel = new JLabel("Введите температуру: ");
        final JTextField inputField = new JTextField(10);
        inputBox.add(inputLabel);
        inputBox.add(Box.createHorizontalStrut(12));
        inputBox.add(inputField);

        Box outBox = Box.createHorizontalBox();
        JLabel outLabel = new JLabel("Значение: ");
        final JTextField outField = new JTextField(10);
        outField.setEditable(false);
        outBox.add(outLabel);
        outBox.add(Box.createHorizontalStrut(12));
        outBox.add(outField);

        Box switchBox = Box.createHorizontalBox();
        JLabel switchLabel1 = new JLabel("Выберите вариант перевода: ");
        String[] elementsOfComboBox = {"Цельсий", "Кельвин", "Фаренгейт"};
        final JComboBox<String> comboBox1 = new JComboBox<>(elementsOfComboBox);
        comboBox1.setSelectedIndex(0);
        JLabel switchLabel2 = new JLabel("=>");
        final JComboBox<String> comboBox2 = new JComboBox<>(elementsOfComboBox);
        comboBox2.setSelectedIndex(1);
        switchBox.add(switchLabel1);
        switchBox.add(Box.createHorizontalStrut(12));
        switchBox.add(comboBox1);
        switchBox.add(Box.createHorizontalStrut(12));
        switchBox.add(switchLabel2);
        switchBox.add(Box.createHorizontalStrut(12));
        switchBox.add(comboBox2);

        inputLabel.setPreferredSize(switchLabel1.getPreferredSize());
        outLabel.setPreferredSize(switchLabel1.getPreferredSize());

        JButton transfer = new JButton("Перевести");
        transfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                outField.setText(transfer(Double.valueOf(inputField.getText()), comboBox1.getSelectedIndex(),
                        comboBox2.getSelectedIndex()));
            }
        });

        Box mainBox = Box.createVerticalBox();
        this.add(mainBox);

        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(inputBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(outBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(switchBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(transfer);

        this.pack();
    }

    private String transfer(double inputTemperature, int switch1, int switch2) {
        final double KELVIN_COEFFICIENT = 273.15;
        final double FAHRENHEIT_COEFFICIENT_1 = 1.8;
        final double FAHRENHEIT_COEFFICIENT_2 = 32;

        if (switch1 == 0) {
            switch (switch2) {
                case 0:
                    return Double.toString(inputTemperature);
                case 1:
                    return Double.toString(inputTemperature + KELVIN_COEFFICIENT);
                case 2:
                    return Double.toString((inputTemperature * FAHRENHEIT_COEFFICIENT_1) + FAHRENHEIT_COEFFICIENT_2);
            }
        }
        if (switch1 == 1) {
            switch (switch2) {
                case 0:
                    return Double.toString(inputTemperature - KELVIN_COEFFICIENT);
                case 1:
                    return Double.toString(inputTemperature);
                case 2:
                    return Double.toString((inputTemperature - KELVIN_COEFFICIENT) * FAHRENHEIT_COEFFICIENT_1 +
                            FAHRENHEIT_COEFFICIENT_2);
            }
        }
        switch (switch2) {
            case 0:
                return Double.toString((inputTemperature - FAHRENHEIT_COEFFICIENT_2) * (1 / FAHRENHEIT_COEFFICIENT_1));
            case 1:
                return Double.toString((inputTemperature - FAHRENHEIT_COEFFICIENT_2) * (1 / FAHRENHEIT_COEFFICIENT_1) +
                        KELVIN_COEFFICIENT);
            default:
                return Double.toString(inputTemperature);
        }
    }
}
