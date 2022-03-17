package com.example.testokenit.controllers;

import com.example.testokenit.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${upload.path}")
    private String path;

    @Autowired
    private ReadFileService readFileService;

    @GetMapping("test")
    public void showInfo() {
        if (readFileService.isValid()) {
            readFileService.writeToDb(readFileService.readFromFile(path));
        }

    }
}
