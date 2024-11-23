package com.insilicogen.healthcareproject.layer.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insilicogen.healthcareproject.layer.application.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SchedulerController {
    private final SchedulerService schedulerService;

    public void updateApiData() throws JsonProcessingException {
        schedulerService.updateApiData();
    }

}
