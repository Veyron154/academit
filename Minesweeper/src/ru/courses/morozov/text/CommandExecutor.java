package ru.courses.morozov.text;

import ru.courses.morozov.model.GridOfMines;
import ru.courses.morozov.model.GridOfMinesAbsenceException;
import ru.courses.morozov.text.commands.*;

public class CommandExecutor {
    private GridOfMines grid;

    public CommandExecutor(GridOfMines grid) {
        this.grid = grid;
    }

    public void executeCommand(int commandIndex) {
        Command command = NamesOfCommands.values()[commandIndex].getCommand();
        command.setGrid(grid);
        try {
            command.execute();
        } catch (GridOfMinesAbsenceException e) {
            System.out.println("GridOfMines не передана!");
        }
    }
}
