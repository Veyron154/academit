package ru.courses.morozov.model;

public class GridOfMines {
    private Cell[][] grid;

    public GridOfMines(int countOfColumns, int countOfRows) {
        this.grid = new Cell[countOfColumns][countOfRows];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public void mine(int countOfMines) {
        for (int i = 1; i <= countOfMines; ++i) {
            int row = (int) (Math.random() * this.grid.length);
            int column = (int) (Math.random() * this.grid[0].length);
            if (this.grid[row][column].isMined()) {
                --i;
                continue;
            }
            this.grid[row][column].setMined(true);
        }
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j].isMined()) {
                    continue;
                }
                if (i != 0 && grid[i - 1][j].isMined()) {
                    grid[i][j].upIndex();
                }
                if (j != 0 && grid[i][j - 1].isMined()) {
                    grid[i][j].upIndex();
                }
                if (i != (grid.length - 1) && grid[i + 1][j].isMined()) {
                    grid[i][j].upIndex();
                }
                if (j != (grid[0].length - 1) && grid[i][j + 1].isMined()) {
                    grid[i][j].upIndex();
                }
                if (i != 0 && j != 0 && grid[i - 1][j - 1].isMined()) {
                    grid[i][j].upIndex();
                }
                if (i != 0 && j != (grid[0].length - 1) && grid[i - 1][j + 1].isMined()) {
                    grid[i][j].upIndex();
                }
                if (i != (grid.length - 1) && j != 0 && grid[i + 1][j - 1].isMined()) {
                    grid[i][j].upIndex();
                }
                if (i != (grid.length - 1) && j != (grid[0].length - 1) && grid[i + 1][j + 1].isMined()) {
                    grid[i][j].upIndex();
                }
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
        return this.grid.length;
    }

    public int getCountOfColumns() {
        return this.grid[0].length;
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

    public boolean isMined(int row, int column){return this.grid[row][column].isMined();}

    public void setMark(int row, int column, boolean mark) {
        this.grid[row][column].setMarked(mark);
    }

    public void setOpened(int row, int column, boolean opened) {
        this.grid[row][column].setOpened(opened);
    }

    public void setMined(int row, int column, boolean mined){this.grid[row][column].setMined(mined);}
}

