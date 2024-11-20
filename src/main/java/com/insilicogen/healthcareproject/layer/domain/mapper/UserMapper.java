package com.insilicogen.healthcareproject.layer.domain.mapper;

import com.insilicogen.healthcareproject.layer.domain.model.HealthInfo;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findById(String id);
    void saveUserInfo(User user);
    boolean existsById(String id);
    int saveHealthInfo(UserHealth userHealth);
    List<HealthInfo> getHealthInfo(String id);
    List<HealthInfo> getWeightInfo(String id);
    List<HealthInfo> getBloodInfo(String id);
}
