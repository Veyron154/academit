package ru.courses.morozov;

import ru.courses.morozov.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Temperature {
    private final static TemperatureConverter[][] arrayOfConverters = new TemperatureConverter
            [TemperatureScale.values().length][TemperatureScale.values().length];

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

    private JLabel inputLabel = new JLabel("Введите температуру: ");
    private JLabel outLabel = new JLabel("Значение: ");
    private JLabel switchLabel1 = new JLabel("Выберите вариант перевода: ");
    private JLabel switchLabel2 = new JLabel("=>");
    private JTextField inputField = new JTextField(10);
    private JTextField outField = new JTextField(10);
    private JComboBox<TemperatureScale> comboBox1 = new JComboBox<>(TemperatureScale.values());
    private JComboBox<TemperatureScale> comboBox2 = new JComboBox<>(TemperatureScale.values());
    private JButton convert = new JButton("Перевести");
    private int strut = 12;

    public Temperature() {
        JFrame temperatureFrame = new JFrame();
        temperatureFrame.setTitle("Перевод температур");
        temperatureFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        temperatureFrame.add(createMainBox());

        inputLabel.setPreferredSize(switchLabel1.getPreferredSize());
        outLabel.setPreferredSize(switchLabel1.getPreferredSize());

        convert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!inputField.getText().matches("^\\d*\\.?\\d*$")) {
                    JOptionPane.showMessageDialog(convert, "Введите числовое значение", "Введите число",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    outField.setText(Double.toString(arrayOfConverters[comboBox1.getSelectedIndex()]
                            [comboBox2.getSelectedIndex()].convert(Double.valueOf(inputField.getText()))));
                }
            }
        });

        temperatureFrame.pack();
        temperatureFrame.setLocationRelativeTo(null);
        temperatureFrame.setMinimumSize(new Dimension(temperatureFrame.getWidth(), temperatureFrame.getHeight()));
        temperatureFrame.setVisible(true);
    }

    private Box createInputBox(){
        final Box inputBox = Box.createHorizontalBox();
        inputBox.add(inputLabel);
        inputBox.add(Box.createHorizontalStrut(strut));
        inputBox.add(inputField);
        return inputBox;
    }

    private Box createOutBox(){
        Box outBox = Box.createHorizontalBox();
        outField.setEditable(false);
        outBox.add(outLabel);
        outBox.add(Box.createHorizontalStrut(strut));
        outBox.add(outField);
        return outBox;
    }

    private Box createSwitchBox(){
        Box switchBox = Box.createHorizontalBox();
        switchBox.add(switchLabel1);
        switchBox.add(Box.createHorizontalStrut(strut));
        switchBox.add(comboBox1);
        switchBox.add(Box.createHorizontalStrut(strut));
        switchBox.add(switchLabel2);
        switchBox.add(Box.createHorizontalStrut(strut));
        switchBox.add(comboBox2);
        return switchBox;
    }

    private Box createMainBox(){
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(strut, strut, strut, strut));
        mainBox.add(createInputBox());
        mainBox.add(Box.createVerticalStrut(strut));
        mainBox.add(createOutBox());
        mainBox.add(Box.createVerticalStrut(strut));
        mainBox.add(createSwitchBox());
        mainBox.add(Box.createVerticalStrut(strut));
        mainBox.add(convert);
        convert.setAlignmentX(Component.CENTER_ALIGNMENT);
        return mainBox;
    }
}
