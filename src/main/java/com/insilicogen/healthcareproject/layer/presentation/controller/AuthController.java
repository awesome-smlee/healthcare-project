package com.insilicogen.healthcareproject.layer.presentation.controller;

import com.insilicogen.healthcareproject.layer.application.dto.AuthResponse;
import com.insilicogen.healthcareproject.layer.application.dto.LoginRequest;
import com.insilicogen.healthcareproject.layer.application.security.JwtTokenProvider;
import com.insilicogen.healthcareproject.layer.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    // 로그인
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
        long expiresIn = jwtTokenProvider.getExpirationMs(token) / 1000;

        return ResponseEntity.ok(new AuthResponse(token, expiresIn));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        // Bearer token에서 "Bearer " 부분을 제거
        String jwtToken = token.replace("Bearer ", "");
        return authService.logout(jwtToken);
    }

    // 새로운 토큰 생성
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String oldToken) {
        String jwtToken = oldToken.replace("Bearer ", "");

        // 로그아웃 했는데도 세션이 남아있을 경우 토큰 중복 방지를 위해 추가
        // JWT 토큰이 유효하면 새 토큰을 발급
        if (jwtTokenProvider.validateToken(jwtToken)) {
            String username = jwtTokenProvider.getUsername(jwtToken);
            String newToken = authService.generateNewToken(username);
            long expiresIn = jwtTokenProvider.getExpirationMs(newToken) / 1000;

            // Redis에 새 토큰 저장
            redisTemplate.opsForValue().set(username, newToken, Duration.ofDays(1));

            return ResponseEntity.ok(new AuthResponse(newToken, expiresIn));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }
    }
}
