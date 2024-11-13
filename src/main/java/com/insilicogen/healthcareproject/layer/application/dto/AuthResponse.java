package com.insilicogen.healthcareproject.layer.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private long expiresIn; // 토큰 만료 시간 (초 단위로)

    public AuthResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
