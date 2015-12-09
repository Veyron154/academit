package ru.courses.morozov;

public class GridOfMines {
    private int[][] grid;

    public GridOfMines(int countOfRows, int countOfColumns) {
        this.grid = new int[countOfRows][countOfColumns];
    }

    public void mine(int countOfMines) {
        for (int i = 1; i <= countOfMines; ++i) {
            int row = (int) (Math.random() * this.grid.length);
            int column = (int) (Math.random() * this.grid[0].length);
            if (this.grid[row][column] == -1) {
                --i;
                continue;
            }
            this.grid[row][column] = -1;
        }
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == -1) {
                    continue;
                }
                if (i != 0 && grid[i - 1][j] == -1) {
                    grid[i][j] += 1;
                }
                if (j != 0 && grid[i][j - 1] == -1) {
                    grid[i][j] += 1;
                }
                if (i != (grid.length - 1) && grid[i + 1][j] == -1) {
                    grid[i][j] += 1;
                }
                if (j != (grid[0].length - 1) && grid[i][j + 1] == -1) {
                    grid[i][j] += 1;
                }
                if (i != 0 && j != 0 && grid[i - 1][j - 1] == -1) {
                    grid[i][j] += 1;
                }
                if (i != 0 && j != (grid[0].length - 1) && grid[i - 1][j + 1] == -1) {
                    grid[i][j] += 1;
                }
                if (i != (grid.length - 1) && j != 0 && grid[i + 1][j - 1] == -1) {
                    grid[i][j] += 1;
                }
                if (i != (grid.length - 1) && j != (grid[0].length - 1) && grid[i + 1][j + 1] == -1) {
                    grid[i][j] += 1;
                }
            }
        }
    }

    public int getIndex(int row, int column) {
        return this.grid[row][column];
    }
}
