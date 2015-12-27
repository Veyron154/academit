package ru.courses.morozov.GUI;

import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.model.Record;
import ru.courses.morozov.model.TableOfRecords;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
    private JLabel[][] mineLabels;
    private GridOfMines grid;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel buttonsPanel = new JPanel();
    private final int SIZE_OF_BUTTON = 25;
    private JLabel label;
    private final int BORDER = 10;
    private final int TEXT_COLUMNS = 3;
    private JTextField timerField = new JTextField(TEXT_COLUMNS);
    private final String PATH_TO_RESOURCE = "Minesweeper/src/ru/courses/morozov/resources/";

    public MineFrame() {
    }

    public MineFrame(int countOfColumns, int countOfRows, int countOfMines) {
        this.countOfColumns = countOfColumns;
        this.countOfRows = countOfRows;
        this.countOfMines = countOfMines;
    }

    public void createFrame() {
        createMenuBar();
        mineFrame.setLayout(new BorderLayout());
        createTopPanel();

        gbc.fill = GridBagConstraints.BOTH;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gbc.weightx = (screenSize.getWidth() / countOfRows) - SIZE_OF_BUTTON;
        gbc.weighty = (screenSize.getHeight() / countOfColumns) - SIZE_OF_BUTTON;

        grid = new GridOfMines(this.countOfColumns, this.countOfRows);

        timer = new Timer(1000, e -> {
            timerText += 1;
            timerField.setText(Integer.toString(timerText));
        });

        buttonsPanel.setLayout(new GridBagLayout());
        mineLabels = new JLabel[countOfColumns][countOfRows];
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
        fill();
    }

    private void fill() {
        timer.stop();
        timerText = 0;
        timerField.setText(Integer.toString(timerText));
        mineCounter = countOfMines;
        mineCounterField.setText(Integer.toString(mineCounter));
        grid.clean();
        grid.mining(countOfMines);
    }

    private void setMark(int row, int column) {
        if (grid.isFlagged(row, column)) {
            grid.setFlag(row, column, false);
            grid.setMark(row, column, true);
            mineButtons[row][column].setIcon(new ImageIcon
                    (PATH_TO_RESOURCE + "question_mark.png"));
            mineCounter += 1;
            mineCounterField.setText(Integer.toString(mineCounter));
        } else if (grid.isMarked(row, column)) {
            grid.setMark(row, column, false);
            mineButtons[row][column].setIcon(null);
        } else {
            grid.setFlag(row, column, true);
            mineButtons[row][column].setIcon(new ImageIcon(PATH_TO_RESOURCE + "flag.png"));
            mineCounter -= 1;
            mineCounterField.setText(Integer.toString(mineCounter));
        }
    }

    private void openButtonsAroundLabel(int row, int column) {
        int rowStart = row - 1;
        int rowEnd = row + 1;
        int columnStart = column - 1;
        int columnEnd = column + 1;

        if (row == 0) {
            rowStart = row;
        }
        if (row == countOfColumns - 1) {
            rowEnd = row;
        }
        if (column == 0) {
            columnStart = column;
        }
        if (column == countOfRows - 1) {
            columnEnd = column;
        }

        for (int i = rowStart; i <= rowEnd; ++i) {
            for (int j = columnStart; j <= columnEnd; ++j) {
                grid.open(i, j);
            }
        }
    }

    private boolean isWin() {
        for (int i = 0; i < this.countOfColumns; ++i) {
            for (int j = 0; j < this.countOfRows; ++j) {
                if (!grid.isMined(i, j) && !grid.isOpened(i, j)) {
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
                    if (grid.isMined(i, j)) {
                        timer.stop();
                        mineReveal();
                        label.setIcon(new ImageIcon(PATH_TO_RESOURCE + "active_mine.png"));
                        JOptionPane.showMessageDialog(mineFrame, "Поражение!", "Поражение!",
                                JOptionPane.PLAIN_MESSAGE);
                        clean();
                        fill();
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
                if (grid.isMined(i, j)) {
                    mineButtons[i][j].setIcon(new ImageIcon(PATH_TO_RESOURCE + "mine.png"));
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
                    newFrame.createFrame();
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
            public void mouseReleased(MouseEvent e) {
                timer.start();
                if (SwingUtilities.isLeftMouseButton(e) && !grid.isFlagged(finalI, finalJ))
                    grid.open(finalI, finalJ);
                openButtons();

                if (isWin()) {
                    timer.stop();
                    List<Record> list = null;
                    try {
                        list = TableOfRecords.deserialize();
                    } catch (IOException | ClassNotFoundException e1) {
                        File newTableOfRecords = new File(PATH_TO_RESOURCE + "table_of_records.bin");
                        try {
                            if (newTableOfRecords.createNewFile()) {
                                TableOfRecords.serialize(new ArrayList<>());
                                list = TableOfRecords.deserialize();
                                JOptionPane.showMessageDialog(mineFrame, "Создана новая таблица рекордов",
                                        "Создан новый файл", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (IOException e2) {
                            JOptionPane.showMessageDialog(mineFrame, "Таблица рекордов отсутствует",
                                    "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                        } catch (ClassNotFoundException e2) {
                            e2.printStackTrace();
                        }
                    }
                    assert list != null;
                    list.add(new Record(timerText, new SimpleDateFormat("dd.MM.yyyy hh.mm").format(new Date())));
                    try {
                        TableOfRecords.serialize(list);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(mineFrame, "Результат не сохранён",
                                "Ошибка сохранения результата", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(mineFrame, "Ваше время: " + timerText, "Победа!",
                            JOptionPane.PLAIN_MESSAGE);
                    clean();
                    fill();
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
        final int SIZE_OF_TEXT = 18;
        label.setFont(new Font("Verdana", Font.BOLD, SIZE_OF_TEXT));
        label.setForeground(getColorOfText(i, j));
        final int finalI1 = i;
        final int finalJ1 = j;
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e) && checkFlags(finalI1, finalJ1)) {
                    openButtonsAroundLabel(finalI1, finalJ1);
                    openButtons();
                }
                if (isWin()) {
                    timer.stop();
                    List<Record> list = null;
                    try {
                        list = TableOfRecords.deserialize();
                    } catch (IOException | ClassNotFoundException e1) {
                        File newTableOfRecords = new File(PATH_TO_RESOURCE + "table_of_records.bin");
                        try {
                            if (newTableOfRecords.createNewFile()) {
                                TableOfRecords.serialize(new ArrayList<>());
                                list = TableOfRecords.deserialize();
                                JOptionPane.showMessageDialog(mineFrame, "Создана новая таблица рекордов",
                                        "Создан новый файл", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (IOException e2) {
                            JOptionPane.showMessageDialog(mineFrame, "Таблица рекордов отсутствует",
                                    "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                        } catch (ClassNotFoundException e2) {
                            e2.printStackTrace();
                        }
                    }
                    assert list != null;
                    list.add(new Record(timerText, new SimpleDateFormat("dd.MM.yyyy hh.mm").format(new Date())));
                    try {
                        TableOfRecords.serialize(list);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(mineFrame, "Результат не сохранён",
                                "Ошибка сохранения результата", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(mineFrame, "Ваше время: " + timerText, "Победа!",
                            JOptionPane.PLAIN_MESSAGE);
                    clean();
                    fill();
                }
            }
        });
        buttonsPanel.add(label, gbc);
        mineLabels[i][j] = label;
    }

    private void clean() {
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                mineButtons[i][j].setIcon(null);
                mineButtons[i][j].setVisible(true);
                if (mineLabels[i][j] != null) {
                    mineLabels[i][j].setText(null);
                    mineLabels[i][j].setIcon(null);
                }
            }
        }
    }

    private boolean checkFlags(int row, int column) {
        int rowStart = row - 1;
        int rowEnd = row + 1;
        int columnStart = column - 1;
        int columnEnd = column + 1;
        int countOfFlags = 0;
        if (row == 0) {
            rowStart = row;
        }
        if (row == countOfColumns - 1) {
            rowEnd = row;
        }
        if (column == 0) {
            columnStart = column;
        }
        if (column == countOfRows - 1) {
            columnEnd = column;
        }
        for (int i = rowStart; i <= rowEnd; ++i) {
            for (int j = columnStart; j <= columnEnd; ++j) {
                if (grid.isFlagged(i, j)) {
                    countOfFlags++;
                }
            }
        }
        return grid.getIndex(row, column) == countOfFlags;
    }

    private Color getColorOfText(int i, int j) {
        switch (grid.getIndex(i, j)) {
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.red;
            case 4:
                return Color.magenta;
            case 5:
                return Color.gray;
            case 6:
                return Color.cyan;
            case 7:
                return Color.yellow;
            default:
                return Color.black;
        }
    }

    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Игра");
        menuBar.add(gameMenu);

        JMenuItem newGame = new JMenuItem("Новая игра");
        newGame.addActionListener(e -> showNewGameFrame());
        newGame.setMnemonic('в');
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        gameMenu.add(newGame);

        JMenuItem highScore = new JMenuItem("Таблица рекордов");
        highScore.addActionListener(e1 -> {
            try {
                JOptionPane.showMessageDialog(mineFrame, TableOfRecords.tableToString(), "Таблица рекордов",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                File newTableOfRecords = new File(PATH_TO_RESOURCE + "table_of_records.bin");
                try {
                    if (newTableOfRecords.createNewFile()) {
                        TableOfRecords.serialize(new ArrayList<>());
                        JOptionPane.showMessageDialog(mineFrame, "Создана новая таблица рекордов", "Создан новый файл",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(mineFrame, "Таблица рекордов отсутствует", "Ошибка создания файла",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        highScore.setMnemonic('а');
        highScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        gameMenu.add(highScore);

        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(e -> mineFrame.dispose());
        exit.setMnemonic('ы');
        gameMenu.add(exit);

        JMenu aboutMenu = new JMenu("Справка");
        menuBar.add(aboutMenu);

        JMenuItem about = new JMenuItem("О программе");
        about.addActionListener(e1 -> JOptionPane.showMessageDialog
                (mineFrame, "Игра сапёр\nАвтор: Павел Морозов\nacademITschool, 2015\nv 1.3", "О программе",
                        JOptionPane.INFORMATION_MESSAGE));
        about.setMnemonic('м');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutMenu.add(about);

        mineFrame.setJMenuBar(menuBar);
    }

    public void createTopPanel() {
        JPanel topPanel = new JPanel();

        JLabel mineCounterLabel = new JLabel();
        mineCounterLabel.setIcon(new ImageIcon(PATH_TO_RESOURCE + "mine.png"));
        topPanel.add(mineCounterLabel);

        mineCounterField = new JTextField(TEXT_COLUMNS);
        mineCounterField.setEditable(false);
        int SIZE_OF_FONT = 17;
        mineCounterField.setFont(new Font("font", Font.BOLD, SIZE_OF_FONT));
        mineCounterField.setHorizontalAlignment(JTextField.RIGHT);
        topPanel.add(mineCounterField);

        JButton smile = new JButton();
        final int SIZE_OF_SMILE = 40;
        smile.setPreferredSize(new Dimension(SIZE_OF_SMILE, SIZE_OF_SMILE));
        smile.setIcon(new ImageIcon(PATH_TO_RESOURCE + "smile.png"));
        smile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clean();
                fill();
            }
        });
        topPanel.add(smile);

        timerField.setFont(new Font("F", Font.BOLD, SIZE_OF_FONT));
        timerField.setEditable(false);
        timerField.setHorizontalAlignment(JTextField.RIGHT);
        topPanel.add(timerField);

        JLabel timerLabel = new JLabel();
        timerLabel.setIcon(new ImageIcon(PATH_TO_RESOURCE + "timer.png"));
        topPanel.add(timerLabel);

        mineFrame.add(topPanel, BorderLayout.NORTH);
    }
}

