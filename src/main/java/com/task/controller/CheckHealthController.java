package com.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "check-health")
public class CheckHealthController {

    @GetMapping
    public String checkHealth() {
        return "Hello javier";
    }
}
