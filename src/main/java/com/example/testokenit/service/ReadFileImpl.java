package com.example.testokenit.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReadFileImpl implements ReadFileService{

    @Scheduled(cron = "${interval-in-cron}")
    public void read(){
        System.out.println("Запуск по расписанию. Метод read класса ReadFile - выполнен");
    }
}
