package com.insilicogen.healthcareproject.layer.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHealth {
    private String id;
    private HealthInfo healthInfo;
}
