package com.insilicogen.healthcareproject.layer.application.service;

import com.insilicogen.healthcareproject.layer.domain.model.User;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> saveHealthInfo(UserHealth healthData);
}
