package com.insilicogen.healthcareproject.layer.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HealthInfo {
    private int height; // 키
    private int weight; // 체중
    private int bloodPressure; // 수축기 혈압
    private int bloodPressure2; // 이완기 혈압
    private int heartRate; // 심박수
}
