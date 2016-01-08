package ru.courses.morozov.text;

public enum NamesOfCommands {
    OPEN_CELL("Открыть ячейку.", new CellOpener()),
    SET_FLAG("Поставить флаг.", new FlagSetter()),
    SET_MARK("Поставить вопрос.", new MarkSetter()),
    REMOVE_MARKS("Убрать знаки.", new MarkRemover()),
    OPEN_CELL_AROUND_FIELD("Открыть ячейки вокруг поля.", new AroundCellOpener()),
    RESTART_GAME("Перезапустить игру.", new GameRestarter()),
    NEW_GAME("Новая игра.", new NewGameStarter()),
    HIGH_SCORE("Таблица рекордов.", new HighScorePrinter()),
    INFO("Информация.", new InfoPrinter()),
    EXIT("Выход.", new Finisher());

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
