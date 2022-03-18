package com.example.testokenit.service;

import java.util.List;

public interface ReadFileService {

    // Сохранение информации в logs.txt
    boolean saveLogs(String message);

    // Получение полного пути до нужного файла
    String getFilePath(String path);

    // Валидация расширения файла и количество
    boolean isValid();

    // Получение данных из прочтенного файла
    List<String[]> readFromFile(String path);

    // Запись в таблицы БД
    boolean writeToDb(List<String[]> data);

    // Выполнение задач по расписанию
    void uploadData();

    // Удаление файла по пути
    boolean deleteFile(String path);


}
