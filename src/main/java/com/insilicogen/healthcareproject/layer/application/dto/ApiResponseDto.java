package com.insilicogen.healthcareproject.layer.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponseDto {
    private List<DataItem> items;

    @Data
    public static class DataItem {
        private String category;
        private String value;
    }
}