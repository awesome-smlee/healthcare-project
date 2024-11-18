package com.insilicogen.healthcareproject.layer.application.service.impl;

import com.insilicogen.healthcareproject.layer.domain.mapper.UserMapper;
import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        log.info("loginId 확인 : " + id);
        User user = null;
        try {
            user = userMapper.findById(id);
        } catch (Exception e) {
            log.error("데이터베이스에서 사용자 검색 실패", e);
        }

        if(user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다. id: " + id);
        }

        // 사용자 정보를 Spring Security에서 사용할 수 있도록 변환하여 반환
        log.info("사용자 정보: " + user.toString());

        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPassword(),
                true, true, true, true,
                List.of(user::getRole));  // 스프링 시큐리티 UserDetails로 변환
    }

}
