package ru.courses.morozov.text.commands;

public enum NamesOfCommands {
    OPEN_CELL("Открыть ячейк", new OpenCellCommand()),
    SET_FLAG("Поставить флаг", new SetFlagCommand()),
    SET_MARK("Поставить вопрос", new SetMarkCommand()),
    REMOVE_MARKS("Убрать знаки", new RemoveMarksCommand()),
    OPEN_CELL_AROUND_FIELD("Открыть ячейки вокруг поля", new OpenCellAroundFieldCommand()),
    RESTART_GAME("Перезапустить игру", new RestartGameCommand()),
    NEW_GAME("Новая игра", new NewGameCommand()),
    HIGH_SCORE("Таблица рекордов", new HighScoreCommand()),
    INFO("Информация", new InfoCommand()),
    EXIT("Выход", new ExitCommand());

    private final String printedString;
    private final Command command;

    NamesOfCommands(String printedString, Command command) {
        this.printedString = printedString;
        this.command = command;
    }

    public String getPrintedString() {
        return this.printedString;
    }

    public Command getCommand() {
        return this.command;
    }
}
