package ru.courses.morozov.model;

import java.util.LinkedList;
import java.util.Queue;

public class GridOfMines {
    private Cell[][] grid;
    private int countOfColumns;
    private int countOfRows;

    public GridOfMines(int countOfColumns, int countOfRows) {
        this.countOfColumns = countOfColumns;
        this.countOfRows = countOfRows;
        this.grid = new Cell[countOfColumns][countOfRows];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public void mining(int countOfMines) {
        for (int i = 1; i <= countOfMines; ++i) {
            int row = (int) (Math.random() * this.grid.length);
            int column = (int) (Math.random() * this.grid[0].length);
            if (this.grid[row][column].isMined()) {
                --i;
                continue;
            }
            this.grid[row][column].setMined(true);
        }
        for (int i = 0; i < countOfColumns; ++i) {
            for (int j = 0; j < countOfRows; ++j) {
                int rowStart = i - 1;
                int rowEnd = i + 1;
                int columnStart = j - 1;
                int columnEnd = j + 1;
                if (i == 0) {
                    rowStart = i;
                }
                if (i == countOfColumns - 1) {
                    rowEnd = i;
                }
                if (j == 0) {
                    columnStart = j;
                }
                if (j == countOfRows - 1) {
                    columnEnd = j;
                }
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

                int rowStart = rowIndex - 1;
                int rowEnd = rowIndex + 1;
                int columnStart = columnIndex - 1;
                int columnEnd = columnIndex + 1;

                if (rowIndex == 0) {
                    rowStart = rowIndex;
                }
                if (rowIndex == countOfColumns - 1) {
                    rowEnd = rowIndex;
                }
                if (columnIndex == 0) {
                    columnStart = columnIndex;
                }
                if (columnIndex == countOfRows - 1) {
                    columnEnd = columnIndex;
                }

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

    public int getIndex(int row, int column) {
        return this.grid[row][column].getIndex();
    }

    public boolean isOpened(int row, int column) {
        return this.grid[row][column].isOpened();
    }

    public Cell getCell(int row, int column) {
        return this.grid[row][column];
    }

    public int getCountOfRows() {
        return this.countOfRows;
    }

    public int getCountOfColumns() {
        return this.countOfColumns;
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

    public void setOpened(int row, int column, boolean opened) {
        this.grid[row][column].setOpened(opened);
    }

    public void setMined(int row, int column, boolean mined) {
        this.grid[row][column].setMined(mined);
    }
}

