package ru.courses.morozov.gui;

import ru.courses.morozov.controller.Controller;
import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.model.TableOfRecords;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class MineFrame {
    private int countOfColumns = 9;
    private int countOfRows = 9;
    private int countOfMines = 10;
    private int mineCounter;
    private int timerText = 0;
    private Timer timer;
    private JTextField mineCounterField;
    private JFrame mineFrame = new JFrame("Сапёр");
    private JButton[][] mineButtons;
    private GridOfMines grid;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel buttonsPanel = new JPanel();
    private final int SIZE_OF_BUTTON = 25;
    private JLabel label;
    private final int BORDER = 10;

    public MineFrame() {
    }

    public MineFrame(int countOfColumns, int countOfRows, int countOfMines) {
        this.countOfColumns = countOfColumns;
        this.countOfRows = countOfRows;
        this.countOfMines = countOfMines;
    }

    public void run() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Игра");
        menuBar.add(gameMenu);

        JMenuItem newGame = new JMenuItem("Новая игра");
        newGame.addActionListener(e -> showNewGameFrame());
        gameMenu.add(newGame);

        JMenuItem highScore = new JMenuItem("Таблица рекордов");
        highScore.addActionListener(e1 -> {
            try {
                JOptionPane.showMessageDialog(mineFrame, TableOfRecords.tableToString(), "Таблица рекордов",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        gameMenu.add(highScore);

        JMenuItem about = new JMenuItem("О программе");
        about.addActionListener(e1 -> JOptionPane.showMessageDialog
                (mineFrame, "Игра сапёр\nАвтор: Павел Морозов\nacademITschool, 2015\nv 1.0", "О программе",
                        JOptionPane.INFORMATION_MESSAGE));
        gameMenu.add(about);

        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(e -> mineFrame.dispose());
        gameMenu.add(exit);

        mineFrame.setJMenuBar(menuBar);
        mineFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JLabel mineCounterLabel = new JLabel();
        mineCounterLabel.setIcon(new ImageIcon("Minesweeper/src/ru/courses/morozov/resources/mine.png"));
        topPanel.add(mineCounterLabel);

        mineCounter = countOfMines;
        final int TEXT_COLUMNS = 3;
        mineCounterField = new JTextField(Integer.toString(mineCounter), TEXT_COLUMNS);
        mineCounterField.setEditable(false);
        int SIZE_OF_FONT = 17;
        mineCounterField.setFont(new Font("font", Font.BOLD, SIZE_OF_FONT));
        mineCounterField.setHorizontalAlignment(JTextField.RIGHT);
        topPanel.add(mineCounterField);

        JButton smile = new JButton();
        final int SIZE_OF_SMILE = 40;
        smile.setPreferredSize(new Dimension(SIZE_OF_SMILE, SIZE_OF_SMILE));
        smile.setIcon(new ImageIcon("Minesweeper/src/ru/courses/morozov/resources/smile.png"));
        smile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MineFrame mineFrame = new MineFrame(countOfColumns, countOfRows, countOfMines);
                mineFrame.run();
                MineFrame.this.mineFrame.dispose();
            }
        });
        topPanel.add(smile);

        JTextField timerField = new JTextField(Integer.toString(timerText), TEXT_COLUMNS);
        timerField.setFont(new Font("F", Font.BOLD, SIZE_OF_FONT));
        timerField.setEditable(false);
        timerField.setHorizontalAlignment(JTextField.RIGHT);
        topPanel.add(timerField);

        JLabel timerLabel = new JLabel();
        timerLabel.setIcon(new ImageIcon("Minesweeper/src/ru/courses/morozov/resources/timer.png"));
        topPanel.add(timerLabel);

        mineFrame.add(topPanel, BorderLayout.NORTH);

        gbc.fill = GridBagConstraints.BOTH;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gbc.weightx = (screenSize.getWidth() / countOfRows) - SIZE_OF_BUTTON;
        gbc.weighty = (screenSize.getHeight() / countOfColumns) - SIZE_OF_BUTTON;

        grid = new GridOfMines(this.countOfColumns, this.countOfRows);
        grid.mine(this.countOfMines);

        timer = new Timer(1000, e -> {
            timerText += 1;
            timerField.setText(Integer.toString(timerText));
        });

        buttonsPanel.setLayout(new GridBagLayout());
        mineButtons = new JButton[this.countOfColumns][this.countOfRows];
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                createNewButton(i, j);
            }
        }
        buttonsPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        mineFrame.add(buttonsPanel);
        mineFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mineFrame.setVisible(true);
        mineFrame.pack();
        mineFrame.setMinimumSize(new Dimension(mineFrame.getSize()));
        mineFrame.setLocationRelativeTo(null);
        mineFrame.setResizable(false);
    }

    private void setMark(int row, int column) {
        if (grid.isFlagged(row, column)) {
            grid.setFlag(row, column, false);
            grid.setMark(row, column, true);
            mineButtons[row][column].setIcon(new ImageIcon
                    ("Minesweeper/src/ru/courses/morozov/resources/question_mark.png"));
            mineCounter += 1;
            mineCounterField.setText(Integer.toString(mineCounter));
        } else if (grid.isMarked(row, column)) {
            grid.setMark(row, column, false);
            mineButtons[row][column].setIcon(null);
        } else {
            grid.setFlag(row, column, true);
            mineButtons[row][column].setIcon(new ImageIcon("Minesweeper/src/ru/courses/morozov/resources/flag.png"));
            mineCounter -= 1;
            mineCounterField.setText(Integer.toString(mineCounter));
        }
    }

    private void openLabel(int row, int column) {
        if (row != 0 && !grid.isOpened(row - 1, column)) {
            Controller.open(row - 1, column, grid);
        }
        if (column != 0 && !grid.isOpened(row, column - 1)) {
            Controller.open(row, column - 1, grid);
        }
        if (row != countOfColumns - 1 && !grid.isOpened(row + 1, column)) {
            Controller.open(row + 1, column, grid);
        }
        if (column != countOfRows - 1 && !grid.isOpened(row, column + 1)) {
            Controller.open(row, column + 1, grid);
        }
        if (column != 0 && row != 0 && !grid.isOpened(row - 1, column - 1)) {
            Controller.open(row - 1, column - 1, grid);
        }
        if (column != countOfRows - 1 && row != 0 && !grid.isOpened(row - 1, column + 1)) {
            Controller.open(row - 1, column + 1, grid);
        }
        if (column != 0 && row != countOfColumns - 1 && !grid.isOpened(row + 1, column - 1)) {
            Controller.open(row + 1, column - 1, grid);
        }
        if (column != countOfRows - 1 && row != countOfColumns - 1 && !grid.isOpened(row + 1, column + 1)) {
            Controller.open(row + 1, column + 1, grid);
        }
    }

    private boolean isWin() {
        for (int i = 0; i < this.countOfColumns; ++i) {
            for (int j = 0; j < this.countOfRows; ++j) {
                if (grid.getIndex(i, j) == -1 && !grid.isOpened(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void openButtons() {
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                if (grid.isOpened(i, j) && mineButtons[i][j].isVisible()) {
                    setLabel(i, j);
                    int index = grid.getIndex(i, j);
                    if (grid.getIndex(i, j) == -1) {
                        timer.stop();
                        mineReveal();
                        label.setIcon(new ImageIcon
                                ("Minesweeper/src/ru/courses/morozov/resources/active_mine.png"));
                        JOptionPane.showMessageDialog(mineFrame, "Поражение!", "Поражение!",
                                JOptionPane.PLAIN_MESSAGE);
                        MineFrame mineFrame = new MineFrame(countOfColumns, countOfRows, countOfMines);
                        mineFrame.run();
                        MineFrame.this.mineFrame.dispose();
                    } else if (index != 0) {
                        label.setText(Integer.toString(index));
                    }
                }
            }
        }
    }

    private void mineReveal() {
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                if (grid.getIndex(i, j) == -1) {
                    mineButtons[i][j].setIcon(new ImageIcon("Minesweeper/src/ru/courses/morozov/resources/mine.png"));
                }
            }
        }
    }

    private void showNewGameFrame() {
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
        int reply = JOptionPane.showConfirmDialog(mineFrame, newGamePanel, "Новая игра", JOptionPane.OK_CANCEL_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            if (countOfColumnsField.getText().equals("") || countOfRowsField.getText().equals("") ||
                    countOfMinesField.getText().equals("")) {
                JOptionPane.showMessageDialog(mineFrame, "Введите значения", "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!countOfColumnsField.getText().matches("^\\d*$") || !countOfRowsField.getText().matches("^\\d*$")
                    || !countOfMinesField.getText().matches("^\\d*$")) {
                JOptionPane.showMessageDialog(mineFrame, "Введите числовые значения", "Введите значения",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int tmpRows = Integer.valueOf(countOfRowsField.getText());
                int tmpColumns = Integer.valueOf(countOfColumnsField.getText());
                int tmpMines = Integer.valueOf(countOfMinesField.getText());
                if (tmpRows < 9 || tmpRows > 24 || tmpColumns < 9 || tmpColumns > 30 || tmpMines < 10 ||
                        tmpMines > 668) {
                    JOptionPane.showInputDialog(mineFrame, "Введите корректные значения", "Введите значения",
                            JOptionPane.ERROR_MESSAGE);
                } else if (tmpMines > tmpColumns * tmpRows) {
                    JOptionPane.showMessageDialog(mineFrame, "Число мин не должно превышать число ячеек поля",
                            "Введите значения", JOptionPane.ERROR_MESSAGE);
                } else {
                    MineFrame newFrame = new MineFrame(Integer.valueOf(countOfColumnsField.getText()),
                            Integer.valueOf(countOfRowsField.getText()), Integer.valueOf(countOfMinesField.getText()));
                    newFrame.run();
                    mineFrame.dispose();
                }
            }
        }
    }

    private void createNewButton(int i, int j) {
        mineButtons[i][j] = new JButton();
        mineButtons[i][j].setPreferredSize(new Dimension(SIZE_OF_BUTTON, SIZE_OF_BUTTON));
        mineButtons[i][j].setBackground(Color.cyan);

        final int finalI = i;
        final int finalJ = j;

        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer.start();
                if (SwingUtilities.isLeftMouseButton(e) && !grid.isFlagged(finalI, finalJ))
                    Controller.open(finalI, finalJ, grid);
                openButtons();

                if (isWin()) {
                    timer.stop();
                    TreeMap<Integer, String> map = null;
                    try {
                        map = TableOfRecords.deserialize();
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    assert map != null;
                    map.put(timerText, new SimpleDateFormat("dd.MM.yyyy hh.mm").format(new Date()));
                    try {
                        TableOfRecords.serialize(map);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(mineFrame, "Ваше время: " + timerText, "Победа!",
                            JOptionPane.PLAIN_MESSAGE);
                    MineFrame mineFrame = new MineFrame(countOfColumns, countOfRows, countOfMines);
                    mineFrame.run();
                    MineFrame.this.mineFrame.dispose();
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    setMark(finalI, finalJ);
                }
            }
        };
        mineButtons[i][j].addMouseListener(listener);
        gbc.gridx = i;
        gbc.gridy = j;
        buttonsPanel.add(mineButtons[i][j], gbc);
    }

    private void setLabel(int i, int j) {
        mineButtons[i][j].setVisible(false);
        gbc.gridx = i;
        gbc.gridy = j;
        label = new JLabel();
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(SIZE_OF_BUTTON, SIZE_OF_BUTTON));
        label.setMinimumSize(new Dimension(SIZE_OF_BUTTON, SIZE_OF_BUTTON));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        final int finalI1 = i;
        final int finalJ1 = j;
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    openLabel(finalI1, finalJ1);
                    openButtons();
                }
                if (isWin()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(mineFrame, "Ваше время: " + timerText, "Победа!",
                            JOptionPane.PLAIN_MESSAGE);
                    MineFrame mineFrame = new MineFrame(countOfColumns, countOfRows, countOfMines);
                    mineFrame.run();
                    MineFrame.this.mineFrame.setVisible(false);
                }
            }
        });
        buttonsPanel.add(label, gbc);
    }
}

