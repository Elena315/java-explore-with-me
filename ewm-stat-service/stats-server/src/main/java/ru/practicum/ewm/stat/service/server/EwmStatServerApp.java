package ru.practicum.ewm.stat.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EwmStatServerApp {
    public static void main(String[] args) {
        System.setProperty("server.port", "9090");
        SpringApplication.run(EwmStatServerApp.class, args);
    }
}