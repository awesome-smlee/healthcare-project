package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.application.service.UserService;
import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper; // mariaDB

}
