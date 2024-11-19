package com.insilicogen.healthcareproject.layer.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class HealthInfo {
    private final int height; // 키
    private final int weight; // 체중
    private final int bloodPressure; // 수축기 혈압
    private final int bloodPressure2; // 이완기 혈압
    private final int heartRate; // 심박수
    private final LocalDate date;

    @Override
    public String toString() {
        return "HealthInfo{" +
                "height=" + height +
                ", weight=" + weight +
                ", bloodPressure=" + bloodPressure +
                ", bloodPressure2=" + bloodPressure2 +
                ", heartRate=" + heartRate +
                ", date=" + date +
                '}';
    }
}
