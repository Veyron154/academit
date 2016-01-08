package ru.courses.morozov.text;


import ru.courses.morozov.model.TableOfRecordSaveException;
import ru.courses.morozov.model.TableOfRecords;
import ru.courses.morozov.model.TableOfRecordsLoadException;

public class HighScorePrinter extends Command {

    public void execute() {
        final String PATH_TO_TABLE = "Minesweeper/src/ru/courses/morozov/resources/table_of_records.bin";
        final int DEFAULT_CAPACITY_OF_TABLE = 5;
        TableOfRecords tableOfRecords = null;
        try {
            tableOfRecords = new TableOfRecords(PATH_TO_TABLE, DEFAULT_CAPACITY_OF_TABLE, false);
        } catch (TableOfRecordsLoadException | TableOfRecordSaveException e) {
            try {
                tableOfRecords = new TableOfRecords(PATH_TO_TABLE, DEFAULT_CAPACITY_OF_TABLE, true);
                System.out.println("Создан новый файл таблицы рекордов");
            } catch (TableOfRecordSaveException | TableOfRecordsLoadException e1) {
                System.out.println("Ошибка создания таблицы рекордов");
            }
        }
        assert tableOfRecords != null;
        System.out.println(tableOfRecords.tableToString());
    }
}
