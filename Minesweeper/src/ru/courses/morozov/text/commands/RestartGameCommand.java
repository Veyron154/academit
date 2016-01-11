package ru.courses.morozov.text.commands;

import ru.courses.morozov.model.GridOfMinesAbsenceException;

public class RestartGameCommand extends Command {

    public void execute() throws GridOfMinesAbsenceException {
        if (getGrid() == null) {
            throw new GridOfMinesAbsenceException();
        }
        getGrid().clean();
        getGrid().setFilled(false);
    }
}
