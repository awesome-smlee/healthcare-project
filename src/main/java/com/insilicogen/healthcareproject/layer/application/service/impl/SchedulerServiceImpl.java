package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insilicogen.healthcareproject.layer.application.dto.ApiResponseDto;
import com.insilicogen.healthcareproject.layer.application.service.SchedulerService;
import com.insilicogen.healthcareproject.layer.domain.mapper.SchedulerMapper;
import com.insilicogen.healthcareproject.layer.domain.model.TotalAdultFatTrendEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerMapper schedulerMapper;
    private final ObjectMapper objectMapper;

//    @Scheduled(cron = "0 0 * * * *")  // 매 정각마다 호출
    public void updateApiData() {
        String serviceKey = "LCWmnIczv2OtFTfyMKhBXPhaEn50RvRPif6TLVJDgmK%2Be1G9i5o9Qz5fdb6U46KJD3pcIKyJGd0cbUnc4Q14cw%3D%3D";
        String url = "https://apis.data.go.kr/B551172/getPreventFat"; // base URL

        WebClient webClient = WebClient.builder().baseUrl(url).build();

        try {
            // WebClient 사용하여 API 호출
            String response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/TotalAdultFatTrend")
                            .queryParam("serviceKey", serviceKey)
                            .queryParam("pageNo", 1)
                            .queryParam("numOfRows", 100)
                            .queryParam("resultType", "json")
                            .build())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse -> {
                        log.error("API 호출 실패 : " + clientResponse.statusCode());
                        return clientResponse.createException().flatMap(Mono::error);
                    })
                    .bodyToMono(String.class)
                    .block(); // 동기적으로 응답 대기

            log.info("Received response: " + response);

            // JSON 문자열을 DTO 객체로 변환
            ApiResponseDto apiResponse = objectMapper.readValue(response, ApiResponseDto.class);

            // DB에 삽입 또는 업데이트
            for (ApiResponseDto.DataItem item : apiResponse.getItems()) {
                TotalAdultFatTrendEntity entity = new TotalAdultFatTrendEntity();
                entity.setCategory(item.getCategory());
                entity.setValue(Double.valueOf(item.getValue()));

                // 데이터가 존재하는지 확인
                TotalAdultFatTrendEntity existingEntity = schedulerMapper.findByCategory(item.getCategory());
                if (existingEntity != null) {
                    // 데이터가 존재하면 업데이트
                    schedulerMapper.updateApiData(entity);
                    log.info("Updated data for category: {}", item.getCategory());
                } else {
                    // 데이터가 없으면 삽입
                    schedulerMapper.insertApiData(entity);
                    log.info("Inserted new data for category: {}", item.getCategory());
                }
            }

        } catch (Exception e) {
            log.error("API 호출 중 오류 발생: ", e);
        }
    }
}