package ru.courses.morozov.model;

public class Cell {
    private int index;
    private boolean flagged;
    private boolean opened;
    private boolean marked;
    private boolean mined;
    private int rowIndex;
    private int columnIndex;

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

    public boolean isMined(){
        return this.mined;
    }

    public void upIndex() {
        this.index += 1;
    }

    public void setIndex(int index) {
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

    public void setMined(boolean mined){this.mined = mined;}
}

