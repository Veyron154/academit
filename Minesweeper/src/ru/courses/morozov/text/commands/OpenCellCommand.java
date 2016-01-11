package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMinesAbsenceException;

public class OpenCellCommand extends Command {

    public void execute() throws GridOfMinesAbsenceException {
        if (getGrid() == null) {
            throw new GridOfMinesAbsenceException();
        }
        scan();
        if (!getGrid().isFilled()) {
            getGrid().mining(getColumn(), getRow());
            getGrid().setFilled(true);
        }
        if (!getGrid().isFlagged(getColumn(), getRow())) {
            getGrid().open(getColumn(), getRow());
        }
    }
}
