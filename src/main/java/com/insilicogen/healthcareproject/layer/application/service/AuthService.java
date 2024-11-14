package com.insilicogen.healthcareproject.layer.application.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> logout(String jwtToken);
    String generateNewToken(String username);
}
