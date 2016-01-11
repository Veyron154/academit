package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMinesAbsenceException;

public class OpenCellAroundFieldCommand extends Command {

    public void execute() throws GridOfMinesAbsenceException {
        if (getGrid() == null) {
            throw new GridOfMinesAbsenceException();
        }
        scan();
        if (getGrid().isOpened(getColumn(), getRow()) && getGrid().checkFlags(getColumn(), getRow())) {
            getGrid().openButtonsAroundLabel(getColumn(), getRow());
        }
    }
}
