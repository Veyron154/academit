package ru.courses.morozov.GUI;

import ru.courses.morozov.GridOfMines;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineFrame {
    private int countOfColumns = 9;
    private int countOfRows = 9;
    private int countOfMines = 10;
    private JButton[][] mineButtons = new JButton[countOfColumns][countOfRows];
    private GridBagConstraints gbc = new GridBagConstraints();
    private int sizeOfButton = 25;

    public MineFrame() {
    }// TODO: 09.12.2015 добавить конструктор

    public void run() {
        JFrame mineFrame = new JFrame();
        mineFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JButton("smile"));// TODO: 09.12.2015 вызов новой игры
        mineFrame.add(topPanel, BorderLayout.NORTH);

        gbc.fill = GridBagConstraints.BOTH;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gbc.weightx = (screenSize.getWidth() / countOfRows) - sizeOfButton;
        gbc.weighty = (screenSize.getHeight() / countOfColumns) - sizeOfButton;

        GridOfMines grid = new GridOfMines(this.countOfColumns, this.countOfRows);
        grid.mine(countOfMines);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                mineButtons[i][j] = new JButton();
                mineButtons[i][j].setPreferredSize(new Dimension(25, 25));
                mineButtons[i][j].setBackground(Color.cyan);
                final int finalI = i;
                final int finalJ = j;

                MouseListener listener = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        MineFrame.this.mineButtons[finalI][finalJ].setVisible(false);
                        JLabel label = new JLabel();
                        if (grid.getIndex(finalI, finalJ) == -1) {
                            label.setText("*"); // TODO: 09.12.2015 конец игры
                        } else if (grid.getIndex(finalI, finalJ) == 0) {
                            // TODO: 09.12.2015 раскрытие соседних полей
                        } else {
                            label.setText(Integer.toString(grid.getIndex(finalI, finalJ)));
                        }
                        gbc.gridx = finalI;
                        gbc.gridy = finalJ;
                        label.setVerticalAlignment(JLabel.CENTER);
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setPreferredSize(new Dimension(sizeOfButton, sizeOfButton));
                        label.setMinimumSize(new Dimension(sizeOfButton, sizeOfButton));
                        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        buttonsPanel.add(label, gbc);
                    }
                };
                mineButtons[i][j].addMouseListener(listener);
                gbc.gridx = i;
                gbc.gridy = j;
                buttonsPanel.add(mineButtons[i][j], gbc);
            }
        }
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mineFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mineFrame.setVisible(true);
        mineFrame.add(buttonsPanel);
        mineFrame.pack();
        mineFrame.setMinimumSize(new Dimension(mineFrame.getSize()));
        mineFrame.setLocationRelativeTo(null);
    }
}
