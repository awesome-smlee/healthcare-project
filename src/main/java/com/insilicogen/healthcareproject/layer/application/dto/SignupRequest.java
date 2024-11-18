package com.insilicogen.healthcareproject.layer.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupRequest {
    private String id;
    private String password;

    @Override
    public String toString() {
        return "SignupRequest{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
