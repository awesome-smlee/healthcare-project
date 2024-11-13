package com.insilicogen.healthcareproject.layer.presentation.controller;

import com.insilicogen.healthcareproject.layer.application.dto.SignupRequest;
import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignupController {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 API
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {

        log.info("넘어온 요청 데이터"+signupRequest.toString());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // User 객체 생성
        User user = User.builder()
            .id(signupRequest.getId())
            .password(encodedPassword)
            .role("USER").build();

        // DB에 사용자 정보 저장
        userMapper.saveUserInfo(user);

        return "회원가입이 완료되었습니다.";
    }
}