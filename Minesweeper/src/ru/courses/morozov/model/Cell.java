package ru.courses.morozov.model;

public class Cell {
    private int index;

    private int rowIndex;
    private int columnIndex;

    private boolean flagged;
    private boolean opened;
    private boolean marked;
    private boolean mined;

    public Cell(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColumnIndex() {
        return this.columnIndex;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isFlagged() {
        return this.flagged;
    }

    public boolean isOpened() {
        return this.opened;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public boolean isMined() {
        return this.mined;
    }

    public void upIndex() {
        this.index += 1;
    }

    public void setIndex(int index) {
        if (index < 0 || index > 8) {
            throw new IllegalArgumentException("Индекс ячейки должен лежать в пределах от 0 до 8");
        }
        this.index = index;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }
}

