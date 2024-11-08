package com.insilicogen.healthcareproject.layer.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class DataController {
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}