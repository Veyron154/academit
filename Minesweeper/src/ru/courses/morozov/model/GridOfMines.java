package ru.courses.morozov.model;

import java.util.LinkedList;
import java.util.Queue;

public class GridOfMines {
    private static final int DEFAULT_GRID_WIDTH = 9;
    private static final int DEFAULT_GRID_HEIGHT = 9;
    private static final int DEFAULT_COUNT_OF_MINES = 10;

    private Cell[][] grid;

    private int countOfColumns;
    private int countOfRows;
    private int countOfMines;

    private int rowStart;
    private int rowEnd;
    private int columnStart;
    private int columnEnd;

    private boolean filled;

    public GridOfMines(){
        this(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT, DEFAULT_COUNT_OF_MINES);
    }

    public GridOfMines(int countOfColumns, int countOfRows, int countOfMines) {
        this.countOfColumns = countOfColumns;
        this.countOfRows = countOfRows;
        this.countOfMines = countOfMines;
        this.grid = new Cell[countOfColumns][countOfRows];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public void mining(int row, int column) {
        setLimitsOfCycles(row, column);
        outer:
        for (int i = 1; i <= countOfMines; ++i) {
            int tmpRow = (int) (Math.random() * this.countOfColumns);
            int tmpColumn = (int) (Math.random() * this.countOfRows);
            if (this.grid[tmpRow][tmpColumn].isMined()) {
                --i;
                continue;
            }
            for (int j = rowStart; j <= rowEnd; ++j) {
                for (int k = columnStart; k <= columnEnd; ++k) {
                    if (tmpRow == j && tmpColumn == k) {
                        --i;
                        continue outer;
                    }
                }
            }
            this.grid[tmpRow][tmpColumn].setMined(true);
        }
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                setLimitsOfCycles(i, j);
                for (int k = rowStart; k <= rowEnd; ++k) {
                    for (int l = columnStart; l <= columnEnd; ++l) {
                        if (grid[k][l].isMined()) {
                            grid[i][j].upIndex();
                        }
                    }
                }
            }
        }
    }

    public void open(int row, int column) {
        Queue<Cell> queue = new LinkedList<>();

        if (!grid[row][column].isFlagged()) {
            grid[row][column].setOpened(true);
            queue.add(grid[row][column]);
        }

        while (!queue.isEmpty()) {
            Cell cell = queue.remove();

            if (cell.getIndex() == 0 && !cell.isMined()) {
                int rowIndex = cell.getRowIndex();
                int columnIndex = cell.getColumnIndex();
                setLimitsOfCycles(rowIndex, columnIndex);
                for (int i = rowStart; i <= rowEnd; ++i) {
                    for (int j = columnStart; j <= columnEnd; ++j) {
                        if (!grid[i][j].isOpened() && !grid[i][j].isFlagged()) {
                            queue.add(grid[i][j]);
                            grid[i][j].setOpened(true);
                        }
                    }
                }
            }
        }
    }

    public void clean() {
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                grid[i][j].setMarked(false);
                grid[i][j].setMined(false);
                grid[i][j].setFlagged(false);
                grid[i][j].setOpened(false);
                grid[i][j].setIndex(0);
            }
        }
    }

    public boolean isWin() {
        for (int i = 0; i < this.countOfColumns; ++i) {
            for (int j = 0; j < this.countOfRows; ++j) {
                if (!grid[i][j].isMined() && !grid[i][j].isOpened()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLose() {
        for (int i = 0; i < this.countOfColumns; ++i) {
            for (int j = 0; j < this.countOfRows; ++j) {
                if (grid[i][j].isOpened() && grid[i][j].isMined()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void openButtonsAroundLabel(int row, int column) {
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
                open(i, j);
            }
        }
    }

    private void setLimitsOfCycles(int row, int column) {
        rowStart = row - 1;
        rowEnd = row + 1;
        columnStart = column - 1;
        columnEnd = column + 1;
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
    }

    public boolean checkFlags(int row, int column) {
        setLimitsOfCycles(row, column);
        int countOfFlags = 0;
        for (int i = rowStart; i <= rowEnd; ++i) {
            for (int j = columnStart; j <= columnEnd; ++j) {
                if (grid[i][j].isFlagged()) {
                    countOfFlags++;
                }
            }
        }
        return grid[row][column].getIndex() == countOfFlags;
    }

    public int getIndex(int row, int column) {
        return this.grid[row][column].getIndex();
    }

    public boolean isOpened(int row, int column) {
        return this.grid[row][column].isOpened();
    }

    public void setFlag(int row, int column, boolean flag) {
        this.grid[row][column].setFlagged(flag);
    }

    public boolean isFlagged(int row, int column) {
        return this.grid[row][column].isFlagged();
    }

    public boolean isMarked(int row, int column) {
        return this.grid[row][column].isMarked();
    }

    public boolean isMined(int row, int column) {
        return this.grid[row][column].isMined();
    }

    public void setMark(int row, int column, boolean mark) {
        this.grid[row][column].setMarked(mark);
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

    public void setCountOfColumns(int countOfColumns){
        this.countOfColumns = countOfColumns;
    }

    public void setCountOfRows(int countOfRows){
        this.countOfRows = countOfRows;
    }

    public void setCountOfMines(int countOfMines){
        this.countOfMines = countOfMines;
    }

    public void setGrid(){
        this.grid = new Cell[countOfColumns][countOfRows];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}

