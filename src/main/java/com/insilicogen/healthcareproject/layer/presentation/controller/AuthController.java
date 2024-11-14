package com.insilicogen.healthcareproject.layer.presentation.controller;

import com.insilicogen.healthcareproject.layer.application.dto.LoginRequest;
import com.insilicogen.healthcareproject.layer.application.dto.SignupRequest;
import com.insilicogen.healthcareproject.layer.application.security.JwtTokenProvider;
import com.insilicogen.healthcareproject.layer.application.service.AuthService;
import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request: {}", loginRequest);
        return authService.login(loginRequest);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return authService.logout(jwtToken);
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }

}
