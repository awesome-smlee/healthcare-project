package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.application.service.UserService;
import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import com.insilicogen.healthcareproject.layer.domain.model.UserHealth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Override
    public ResponseEntity<?> saveHealthInfo(UserHealth healthData) {
        return userMapper.saveHealthInfo(healthData);
    }
}
