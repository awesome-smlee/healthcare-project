package com.insilicogen.healthcareproject.layer.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class Controller {
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
