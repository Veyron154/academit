package ru.courses.morozov.view;

import ru.courses.morozov.model.FactoryManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FactoryFrame {
    private JFrame frame;
    private Border elementsBorder;

    private JTextField bodyStorageCountText;
    private JTextField bodyProducedText;
    private JTextField engineProducedText;
    private JTextField engineStorageCountText;
    private JTextField accessoriesProducedText;
    private JTextField accessoriesStorageCountText;
    private JTextField dealersBoughtText;
    private JTextField dealersStorageCountText;
    private JTextField inProductionText;

    private JSlider bodySlider;
    private JSlider engineSlider;
    private JSlider accessoriesSlider;
    private JSlider dealersSlider;

    private final int panelBorder;

    private final int defaultValueOfBodySlider;
    private final int defaultValueOfEngineSlider;
    private final int defaultValueOfAccessoriesSlider;
    private final int defaultValueOfDealersSlider;

    private FactoryManager manager;

    public FactoryFrame(FactoryManager manager) {
        this.manager = manager;
        frame = new JFrame("Фабрика");
        int border = 5;
        elementsBorder = new EmptyBorder(border, border, border, border);
        defaultValueOfBodySlider = 3;
        defaultValueOfEngineSlider = 4;
        defaultValueOfAccessoriesSlider = 10;
        defaultValueOfDealersSlider = 15;
        panelBorder = 10;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(createProvidersPanel(), BorderLayout.NORTH);
            frame.add(createDealersPanel(), BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
            frame.setVisible(true);
        });
    }

    private JPanel createProvidersPanel() {
        JPanel providersPanel = new JPanel();
        providersPanel.setBorder(new CompoundBorder(new EmptyBorder(panelBorder, panelBorder, panelBorder, panelBorder),
                new TitledBorder("Поставщики")));

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        providersPanel.setLayout(gbl);

        JLabel slidersLabel = createLabel("Скорость выпуска деталей (сек):");
        JLabel producedLabel = createLabel("Произведено:");
        JLabel onStorageLabel = createLabel("На складе:");
        JLabel bodyLabel = createLabel("Кузов");
        JLabel engineLabel = createLabel("Двигатель");
        JLabel accessoryLabel = createLabel("Аксессуары");

        bodySlider = createSlider(defaultValueOfBodySlider);
        bodySlider.addChangeListener(e -> {
            if (!bodySlider.getValueIsAdjusting()) {
                int preparationTime = bodySlider.getValue() * 1000;
                manager.setBodyProviderPreparationTime(preparationTime);
            }
        });
        engineSlider = createSlider(defaultValueOfEngineSlider);
        engineSlider.addChangeListener(e -> {
            if (!engineSlider.getValueIsAdjusting()) {
                int preparationTime = engineSlider.getValue() * 1000;
                manager.setEngineProviderPreparationTime(preparationTime);
            }
        });
        accessoriesSlider = createSlider(defaultValueOfAccessoriesSlider);
        accessoriesSlider.addChangeListener(e -> {
            if (!accessoriesSlider.getValueIsAdjusting()) {
                int preparationTime = accessoriesSlider.getValue() * 1000;
                manager.setAccessoryProviderPreparationTime(preparationTime);
            }
        });

        bodyProducedText = createTextField();
        engineProducedText = createTextField();
        accessoriesProducedText = createTextField();
        bodyStorageCountText = createTextField();
        engineStorageCountText = createTextField();
        accessoriesStorageCountText = createTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        providersPanel.add(slidersLabel, gbc);
        gbc.gridx = 2;
        providersPanel.add(producedLabel, gbc);
        gbc.gridx = 3;
        providersPanel.add(onStorageLabel, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        providersPanel.add(bodyLabel, gbc);
        gbc.gridx = 1;
        providersPanel.add(bodySlider, gbc);
        gbc.gridx = 2;
        providersPanel.add(bodyProducedText, gbc);
        gbc.gridx = 3;
        providersPanel.add(bodyStorageCountText, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        providersPanel.add(engineLabel, gbc);
        gbc.gridx = 1;
        providersPanel.add(engineSlider, gbc);
        gbc.gridx = 2;
        providersPanel.add(engineProducedText, gbc);
        gbc.gridx = 3;
        providersPanel.add(engineStorageCountText, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        providersPanel.add(accessoryLabel, gbc);
        gbc.gridx = 1;
        providersPanel.add(accessoriesSlider, gbc);
        gbc.gridx = 2;
        providersPanel.add(accessoriesProducedText, gbc);
        gbc.gridx = 3;
        providersPanel.add(accessoriesStorageCountText, gbc);

        return providersPanel;
    }

    private JPanel createDealersPanel() {
        JPanel dealersPanel = new JPanel();
        dealersPanel.setBorder(new CompoundBorder(new EmptyBorder(panelBorder, panelBorder, panelBorder, panelBorder),
                new TitledBorder("Дилеры")));

        JLabel sliderLabel = createLabel("Скорость приобретения автомобилей (сек):");
        JLabel boughtLabel = createLabel("Куплено:");
        JLabel onStorageLabel = createLabel("На складе:");
        JLabel inProductionLabel = createLabel("В производстве");

        dealersSlider = createSlider(defaultValueOfDealersSlider);
        dealersSlider.addChangeListener(e -> {
            if (!dealersSlider.getValueIsAdjusting()) {
                int purchaseTime = dealersSlider.getValue() * 1000;
                manager.setDealerPurchaseTime(purchaseTime);
            }
        });

        dealersBoughtText = createTextField();
        dealersStorageCountText = createTextField();
        inProductionText = createTextField();

        int rows = 2;
        int columns = 4;
        dealersPanel.setLayout(new GridLayout(rows, columns));
        dealersPanel.add(sliderLabel);
        dealersPanel.add(boughtLabel);
        dealersPanel.add(onStorageLabel);
        dealersPanel.add(inProductionLabel);
        dealersPanel.add(dealersSlider);
        dealersPanel.add(dealersBoughtText);
        dealersPanel.add(dealersStorageCountText);
        dealersPanel.add(inProductionText);

        return dealersPanel;
    }

    public void setBodyStorageText(String countOnStorage) {
        bodyStorageCountText.setText(countOnStorage);
    }

    public void setBodyProducedText(String countOfBodies) {
        bodyProducedText.setText(countOfBodies);
    }

    public void setEngineStorageText(String countOnStorage) {
        engineStorageCountText.setText(countOnStorage);
    }

    public void setEngineProducedText(String countOfEngines) {
        engineProducedText.setText(countOfEngines);
    }

    public void setAccessoriesStorageText(String countOnStorage) {
        accessoriesStorageCountText.setText(countOnStorage);
    }

    public void setAccessoriesProducedText(String countOfAccessories) {
        accessoriesProducedText.setText(countOfAccessories);
    }

    public void setDealersStorageText(String countOnStorage) {
        dealersStorageCountText.setText(countOnStorage);
    }

    public void setDealersBoughtText(String countOfBoughtCars) {
        dealersBoughtText.setText(countOfBoughtCars);
    }

    public void setInProductionText(String countOfRequests) {
        inProductionText.setText(countOfRequests);
    }

    public int getDefaultValueOfBodySlider() {
        return defaultValueOfBodySlider;
    }

    public int getDefaultValueOfEngineSlider() {
        return defaultValueOfEngineSlider;
    }

    public int getDefaultValueOfAccessoriesSlider() {
        return defaultValueOfAccessoriesSlider;
    }

    public int getDefaultValueOfDealerSlider() {
        return defaultValueOfDealersSlider;
    }

    private JSlider createSlider(int defaultValue) {
        JSlider slider = new JSlider(0, 20, defaultValue);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(elementsBorder);
        return slider;
    }

    private JTextField createTextField() {
        String defaultValue = "0";
        JTextField textField = new JTextField(defaultValue);
        textField.setEditable(false);
        textField.setBorder(elementsBorder);
        return textField;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(elementsBorder);
        return label;
    }

    public void showConfigNoFindMessage() {
        JOptionPane.showMessageDialog(frame, "Конфигурационный файл не найден\nЗагружены значения по умолчанию",
                "Ошибка загрузки файла", JOptionPane.ERROR_MESSAGE);
    }
}
