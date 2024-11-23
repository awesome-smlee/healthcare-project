package com.insilicogen.healthcareproject.layer.domain.model;

import lombok.Data;

@Data
public class TotalAdultFatTrendEntity {
    private Long id;         // Primary Key (자동 증가)
    private String category; // 카테고리
    private Double value;    // 값
}
