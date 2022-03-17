package com.example.testokenit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TestOkenitApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestOkenitApplication.class, args);
    }

}
