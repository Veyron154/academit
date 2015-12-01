package ru.courses.morozov;

import ru.courses.morozov.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Temperature {
    private static TemperatureConverter[][] arrayOfConverters = new TemperatureConverter[3][3];
    static {
        arrayOfConverters[0][0] = new TemperatureConverterSame();
        arrayOfConverters[0][1] = new TemperatureConverterCK();
        arrayOfConverters[0][2] = new TemperatureConverterCF();
        arrayOfConverters[1][0] = new TemperatureConverterKC();
        arrayOfConverters[1][1] = new TemperatureConverterSame();
        arrayOfConverters[1][2] = new TemperatureConverterKF();
        arrayOfConverters[2][0] = new TemperatureConverterFC();
        arrayOfConverters[2][1] = new TemperatureConverterFK();
        arrayOfConverters[2][2] = new TemperatureConverterSame();
    }

    public Temperature() {
        JFrame temperatureFrame = new JFrame();
        temperatureFrame.setVisible(true);
        temperatureFrame.setTitle("Перевод температур");
        temperatureFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Box inputBox = Box.createHorizontalBox();
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
        final JComboBox<TemperatureScale> comboBox1 = new JComboBox<>(TemperatureScale.values());
        comboBox1.setSelectedIndex(0);
        JLabel switchLabel2 = new JLabel("=>");
        final JComboBox<TemperatureScale> comboBox2 = new JComboBox<>(TemperatureScale.values());
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

        final JButton transfer = new JButton("Перевести");
        transfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!inputField.getText().matches("^\\d*\\.?\\d*$")) {
                    JOptionPane.showMessageDialog(transfer, "Введите числовое значение", "Введите число",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    outField.setText(Double.toString(arrayOfConverters[comboBox1.getSelectedIndex()]
                            [comboBox2.getSelectedIndex()].convert(Double.valueOf(inputField.getText()))));
                }
            }
        });

        Box mainBox = Box.createVerticalBox();
        temperatureFrame.add(mainBox);

        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(inputBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(outBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(switchBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(transfer);
        transfer.setAlignmentX(Component.CENTER_ALIGNMENT);

        temperatureFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        temperatureFrame.setLocationRelativeTo(null);

        temperatureFrame.setMinimumSize(new Dimension(temperatureFrame.getWidth(), temperatureFrame.getHeight()));
    }
}
