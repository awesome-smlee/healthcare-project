package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.application.dto.AuthResponse;
import com.insilicogen.healthcareproject.layer.application.dto.LoginRequest;
import com.insilicogen.healthcareproject.layer.application.dto.SignupRequest;
import com.insilicogen.healthcareproject.layer.application.security.JwtTokenProvider;
import com.insilicogen.healthcareproject.layer.application.service.AuthService;
import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getId(), loginRequest.getPassword());
        log.info("loginRequest : "+loginRequest.toString());

        // 인증 완료되면 객체가 부여됨(사용자 정보, 권한 정보)
        Authentication authentication = authenticationManager.authenticate(authToken);
        log.info("Authentication: {}", authentication);

        String token = jwtTokenProvider.createToken(((UserDetails)authentication.getPrincipal()).getUsername());
        log.info("JWT token: {}", token);

        // Redis에 저장
        redisTemplate.opsForValue().set(authentication.getName(), token, Duration.ofDays(1));

        long expiresIn = jwtTokenProvider.getExpirationMs(token) / 1000;

        return new ResponseEntity<>(new AuthResponse(token, expiresIn), HttpStatus.OK);
    }

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

    @Override
    public ResponseEntity<?> signup(SignupRequest signupRequest) {

        // 중복 ID 체크
        if(userMapper.existsById(signupRequest.getId())){
            return new ResponseEntity<>("이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        User user = User.builder()
                .id(signupRequest.getId())
                .password(encodedPassword)
                .role("USER").build();

        try {
            userMapper.saveUserInfo(user);
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생",e);
            return new ResponseEntity<>("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }

}
