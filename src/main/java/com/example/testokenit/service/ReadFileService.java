package com.example.testokenit.service;

import java.util.List;

public interface ReadFileService {
    //По расписанию
    void read();

    boolean saveLogs(String message);

    boolean isValid();
    List<String[]> readFromFile(String path);

    boolean writeToDb(List<String[]> data);


}
