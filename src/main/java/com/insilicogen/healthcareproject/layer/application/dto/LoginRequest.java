package com.insilicogen.healthcareproject.layer.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginRequest {
    private String id;
    private String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'';
    }
}
