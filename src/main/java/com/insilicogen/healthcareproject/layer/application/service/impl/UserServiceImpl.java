package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.application.service.UserService;
import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import com.insilicogen.healthcareproject.layer.domain.model.HealthInfo;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<?> saveHealthInfo(UserHealth userHealth) {

        int result = userMapper.saveHealthInfo(userHealth);
        if(result > 0) {
            return new ResponseEntity<>("등록 성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<HealthInfo> getHealthInfo(String id) {
        return userMapper.getHealthInfo(id);
    }

    @Override
    public List<HealthInfo> getWeightInfo(String id) {
        return userMapper.getWeightInfo(id);
    }
}
