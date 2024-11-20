package com.insilicogen.healthcareproject.layer.application.service;

import com.insilicogen.healthcareproject.layer.domain.model.HealthInfo;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> saveHealthInfo(UserHealth userHealth);
    List<HealthInfo> getHealthInfo(String id);
    List<HealthInfo> getWeightInfo(String id);
    List<HealthInfo> getBloodInfo(String id);
}
