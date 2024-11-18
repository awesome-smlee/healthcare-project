package com.insilicogen.healthcareproject.layer.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserHealth {
    private User user;
    private List<UserHealth> userHealth;
}
