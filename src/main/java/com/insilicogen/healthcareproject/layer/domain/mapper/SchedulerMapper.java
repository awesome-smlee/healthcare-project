package com.insilicogen.healthcareproject.layer.domain.mapper;

import com.insilicogen.healthcareproject.layer.domain.model.TotalAdultFatTrendEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerMapper {
    void insertApiData(TotalAdultFatTrendEntity apiData);
    void updateApiData(TotalAdultFatTrendEntity entity);
    TotalAdultFatTrendEntity findByCategory(String category);
}
