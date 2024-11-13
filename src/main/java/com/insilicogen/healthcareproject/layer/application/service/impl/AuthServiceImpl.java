package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.application.service.AuthService;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void logout() {
        // token에서 로그인한 사용자 정보 조회하고 로그아웃 처리
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info(user.toString());
//
//        if(redisTemplate.opsForValue().get(user.getId()) != null) {
//            redisTemplate.delete(user.getId());
//        }
    }
}
