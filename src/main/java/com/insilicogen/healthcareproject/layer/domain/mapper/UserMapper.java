package com.insilicogen.healthcareproject.layer.domain.mapper;

import com.insilicogen.healthcareproject.layer.domain.model.User;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

@Mapper
public interface UserMapper {
    User findById(String loginId);
    void saveUserInfo(User user);
    boolean existsById(String loginId);
    ResponseEntity<?> saveHealthInfo(UserHealth healthData);
}
