package ru.courses.morozov.GUI;

import ru.courses.morozov.model.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MineFrame {
    private final int SIZE_OF_BUTTON = 25;
    private final String PATH_TO_RESOURCE = "Minesweeper/src/ru/courses/morozov/resources/";

    private int mineCounter;
    private int timerText;

    private Timer timer;
    private TableOfRecords tableOfRecords;
    private JButton[][] mineButtons;
    private JLabel[][] mineLabels;

    private JFrame mineFrame;
    private GridOfMines grid;
    private GridBagConstraints gbc;
    private JPanel buttonsPanel;
    private JLabel mineLabel;
    private JTextField mineCounterField;
    private JTextField timerField;


    public MineFrame() {
        mineFrame = new JFrame("Сапёр");
        int TEXT_COLUMNS = 3;
        mineCounterField = new JTextField(TEXT_COLUMNS);
        timerField = new JTextField(TEXT_COLUMNS);
        final String PATH_TO_TABLE = "Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin";
        final int DEFAULT_CAPACITY_OF_TABLE = 5;
        try {
            tableOfRecords = new TableOfRecords(PATH_TO_TABLE, DEFAULT_CAPACITY_OF_TABLE, false);
        } catch (TableOfRecordsLoadException | TableOfRecordSaveException e) {
            try {
                tableOfRecords = new TableOfRecords(PATH_TO_TABLE, DEFAULT_CAPACITY_OF_TABLE, true);
                JOptionPane.showMessageDialog(mineFrame, "Создан новый файл таблицы рекордов.\nПуть: " + PATH_TO_TABLE,
                        "Создание файла", JOptionPane.INFORMATION_MESSAGE);
            } catch (TableOfRecordSaveException | TableOfRecordsLoadException e1) {
                JOptionPane.showMessageDialog(mineFrame, "Ошибка создания таблицы рекордов",
                        "Ошибка создания таблицы рекордов", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void createFrame() {
        SwingUtilities.invokeLater(() -> {
            createMenuBar();
            mineFrame.setLayout(new BorderLayout());
            createTopPanel();
            createTimer();
            grid = new GridOfMines();
            fillFrame();
            mineFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mineFrame.setVisible(true);
            mineFrame.setLocationRelativeTo(null);
            mineFrame.setResizable(false);
            restartGame();
        });
    }

    private void restartGame() {
        timer.stop();
        timerText = 0;
        timerField.setText(Integer.toString(timerText));
        mineCounter = grid.getCountOfMines();
        mineCounterField.setText(Integer.toString(mineCounter));
        grid.clean();
        grid.setFilled(false);
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

    private void openButtons() {
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            for (int j = 0; j < grid.getCountOfRows(); ++j) {
                if (grid.isOpened(i, j) && mineButtons[i][j].isVisible()) {
                    setLabel(i, j);
                    int index = grid.getIndex(i, j);
                    if (grid.isMined(i, j)) {
                        timer.stop();
                        mineReveal();
                        mineLabel.setIcon(new ImageIcon(PATH_TO_RESOURCE + "active_mine.png"));
                        JOptionPane.showMessageDialog(mineFrame, "Поражение!", "Поражение!",
                                JOptionPane.PLAIN_MESSAGE);
                        clean();
                        restartGame();
                    } else if (index != 0) {
                        mineLabel.setText(Integer.toString(index));
                    }
                }
            }
        }
    }

    private void mineReveal() {
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            for (int j = 0; j < grid.getCountOfRows(); ++j) {
                if (grid.isMined(i, j)) {
                    mineButtons[i][j].setIcon(new ImageIcon(PATH_TO_RESOURCE + "mine.png"));
                }
            }
        }
    }

    private void showNewGameFrame() {
        NewGameDialog newGameFrame = new NewGameDialog(mineFrame, grid.getCountOfColumns(), grid.getCountOfRows(),
                grid.getCountOfMines());
        newGameFrame.createDialog();
        if (newGameFrame.isCorrect()) {
            clean();
            mineFrame.remove(buttonsPanel);
            grid = new GridOfMines(newGameFrame.getCountOfColumns(), newGameFrame.getCountOfRows(),
                    newGameFrame.getCountOfMines());
            fillFrame();
            restartGame();
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
                if (!grid.isFilled()) {
                    grid.mining(finalI, finalJ);
                    grid.setFilled(true);
                }
                timer.start();
                if (SwingUtilities.isLeftMouseButton(e) && !grid.isFlagged(finalI, finalJ)) {
                    grid.open(finalI, finalJ);
                    openButtons();
                }
                if (grid.isWin()) {
                    timer.stop();
                    showWinMessage();
                    clean();
                    restartGame();
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
        mineLabel = new JLabel();
        mineLabel.setVerticalAlignment(JLabel.CENTER);
        mineLabel.setHorizontalAlignment(JLabel.CENTER);
        mineLabel.setPreferredSize(new Dimension(SIZE_OF_BUTTON, SIZE_OF_BUTTON));
        mineLabel.setMinimumSize(new Dimension(SIZE_OF_BUTTON, SIZE_OF_BUTTON));
        mineLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        final int SIZE_OF_TEXT = 18;
        mineLabel.setFont(new Font("Verdana", Font.BOLD, SIZE_OF_TEXT));
        mineLabel.setForeground(getColorOfText(i, j));
        final int finalI1 = i;
        final int finalJ1 = j;
        mineLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e) && grid.checkFlags(finalI1, finalJ1)) {
                    grid.openButtonsAroundLabel(finalI1, finalJ1);
                    openButtons();
                }
                if (grid.isWin()) {
                    timer.stop();
                    showWinMessage();
                    clean();
                    restartGame();
                }
            }
        });
        buttonsPanel.add(mineLabel, gbc);
        mineLabels[i][j] = mineLabel;
    }

    private void clean() {
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            for (int j = 0; j < grid.getCountOfRows(); ++j) {
                mineButtons[i][j].setIcon(null);
                mineButtons[i][j].setVisible(true);
                if (mineLabels[i][j] != null) {
                    mineLabels[i][j].setText(null);
                    mineLabels[i][j].setIcon(null);
                }
            }
        }
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
        highScore.addActionListener(e1 ->
                JOptionPane.showMessageDialog(mineFrame, tableOfRecords.tableToString(), "Таблица рекордов",
                        JOptionPane.INFORMATION_MESSAGE)
        );
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
                (mineFrame, "Игра сапёр\nАвтор: Павел Морозов\nacademITschool, 2015\nv 2.4", "О программе",
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

        mineCounterField.setEditable(false);
        final int SIZE_OF_FONT = 17;
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
                restartGame();
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

    private void fillFrame() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gbc.weightx = (screenSize.getWidth() / grid.getCountOfRows()) - SIZE_OF_BUTTON;
        gbc.weighty = (screenSize.getHeight() / grid.getCountOfColumns()) - SIZE_OF_BUTTON;

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        mineLabels = new JLabel[grid.getCountOfColumns()][grid.getCountOfRows()];
        mineButtons = new JButton[grid.getCountOfColumns()][grid.getCountOfRows()];
        for (int i = 0; i < grid.getCountOfColumns(); ++i) {
            for (int j = 0; j < grid.getCountOfRows(); ++j) {
                createNewButton(i, j);
            }
        }
        final int BORDER = 10;
        buttonsPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        mineFrame.add(buttonsPanel);
        mineFrame.pack();
    }

    private void createTimer() {
        timer = new Timer(1000, e -> {
            timerText += 1;
            timerField.setText(Integer.toString(timerText));
        });
    }

    private void showWinMessage() {
        tableOfRecords.add(new Record(timerText, new SimpleDateFormat("dd.MM.yyyy hh.mm")
                .format(new Date())));
        try {
            tableOfRecords.save();
        } catch (TableOfRecordSaveException e) {
            JOptionPane.showMessageDialog(mineFrame, "Результат не сохранён",
                    "Ошибка сохранения результата", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(mineFrame, "Ваше время: " + timerText, "Победа!",
                JOptionPane.PLAIN_MESSAGE);
    }
}

