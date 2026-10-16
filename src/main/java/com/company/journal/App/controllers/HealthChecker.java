package com.company.journal.App.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChecker {

    @GetMapping("/health-check")
    public String healthChecker(){
        return "OK";
    }
}
