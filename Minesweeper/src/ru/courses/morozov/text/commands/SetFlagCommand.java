package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMinesAbsenceException;

public class SetFlagCommand extends Command {

    public void execute() throws GridOfMinesAbsenceException {
        if (getGrid() == null) {
            throw new GridOfMinesAbsenceException();
        }
        scan();
        getGrid().setFlag(getColumn(), getRow(), true);
        getGrid().setMark(getColumn(), getRow(), false);
    }
}
