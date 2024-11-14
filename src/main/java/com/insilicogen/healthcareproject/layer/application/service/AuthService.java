package com.insilicogen.healthcareproject.layer.application.service;

import com.insilicogen.healthcareproject.layer.application.dto.LoginRequest;
import com.insilicogen.healthcareproject.layer.application.dto.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(LoginRequest loginRequest);
    ResponseEntity<?> logout(String jwtToken);
    ResponseEntity<?> signup(SignupRequest signupRequest);
}
