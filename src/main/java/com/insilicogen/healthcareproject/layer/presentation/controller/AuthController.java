package com.insilicogen.healthcareproject.layer.presentation.controller;

import com.insilicogen.healthcareproject.layer.application.dto.AuthResponse;
import com.insilicogen.healthcareproject.layer.application.dto.LoginRequest;
import com.insilicogen.healthcareproject.layer.application.security.JwtTokenProvider;
import com.insilicogen.healthcareproject.layer.application.service.AuthService;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request: {}", loginRequest);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getId(), loginRequest.getPassword());

        // 인증 완료되면 객체가 부여됨(사용자 정보, 권한 정보)
        Authentication authentication = authenticationManager.authenticate(authToken);
        log.info("Authentication: {}", authentication);

        String token = jwtTokenProvider.createToken(((UserDetails)authentication.getPrincipal()).getUsername());
        log.info("JWT token: {}", token);

        // Redis에 세션 저장
        redisTemplate.opsForValue().set(authentication.getName(), token, Duration.ofDays(1));

        // 만료 시간 (초 단위로 변환)
        long expiresIn = jwtTokenProvider.getExpirationMs() / 1000;

        log.info("만료시간 : " + expiresIn);
        return ResponseEntity.ok(new AuthResponse(token, expiresIn));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }
}
