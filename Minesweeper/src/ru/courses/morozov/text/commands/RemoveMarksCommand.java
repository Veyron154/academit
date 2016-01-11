package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMinesAbsenceException;

public class RemoveMarksCommand extends Command {

    public void execute() throws GridOfMinesAbsenceException {
        if (getGrid() == null) {
            throw new GridOfMinesAbsenceException();
        }
        scan();
        getGrid().setFlag(getColumn(), getRow(), false);
        getGrid().setMark(getColumn(), getRow(), false);
    }
}
