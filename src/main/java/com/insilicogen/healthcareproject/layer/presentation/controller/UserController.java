package com.insilicogen.healthcareproject.layer.presentation.controller;

import com.insilicogen.healthcareproject.layer.application.service.UserService;
import com.insilicogen.healthcareproject.layer.domain.model.HealthInfo;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/saveHealthInfo")
    public ResponseEntity<?> saveHealthInfo(@RequestBody HealthInfo healthInfo) {
        log.info("healthInfo: {}", healthInfo);
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        UserHealth userHealth = UserHealth.builder().id(id).healthInfo(healthInfo).build();
        return userService.saveHealthInfo(userHealth);
    }

    @GetMapping("/getHealthInfo")
    public List<HealthInfo> getHealthInfo() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getHealthInfo(id);
    }

    @GetMapping("/getWeightInfo")
    public List<HealthInfo> getWeightInfo() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getWeightInfo(id);
    }

    @GetMapping("/getBloodInfo")
    public List<HealthInfo> getBloodInfo() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getBloodInfo(id);
    }

    @GetMapping("/getBMIData")
    public List<HealthInfo> getBMIData() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getBMIData(id);
    }

}