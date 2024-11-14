package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.application.security.JwtTokenProvider;
import com.insilicogen.healthcareproject.layer.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseEntity<?> logout(String jwtToken) {
        // jwt 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(jwtToken)) {
            log.warn("유효하지 않거나 만료된 토큰으로 로그아웃 시도: {}", jwtToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그아웃 실패: 유효하지 않은 토큰");
        }

        // 기존 토큰 삭제
        String username = jwtTokenProvider.getUsername(jwtToken);
        redisTemplate.delete(username);

        // 토큰을 블랙리스트로 저장 (만료 시간에 맞춰 자동 삭제 설정)
        redisTemplate.opsForValue().set(jwtToken, "logout", jwtTokenProvider.getExpirationMs(jwtToken), TimeUnit.MILLISECONDS);

        log.info("토큰으로 성공적으로 로그아웃한 사용자: {}", jwtToken);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }

    // 새로운 토큰을 생성하는 메서드
    public String generateNewToken(String username) {
        // 사용자 이름으로 새 JWT 토큰을 생성
        String newToken = jwtTokenProvider.createToken(username);

        // Redis에서 기존 블랙리스트된 토큰이 있다면 제거
        if (redisTemplate.hasKey(newToken)) {
            redisTemplate.delete(newToken);
        }

        return newToken;
    }
}
