package com.insilicogen.healthcareproject.layer.domain.repository;

import com.insilicogen.healthcareproject.layer.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final RedisTemplate<String, User> redisTemplate;

    public void save(User user) {
        redisTemplate.opsForValue().set(user.getId(), user); // LoginId 키로 사용
    }

    public Optional<User> findByUserId(String loginId) {
        User user = redisTemplate.opsForValue().get(loginId);
        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다.");
        }
        return Optional.ofNullable(user); // 존재하지 않으면 Optional.empty() 반환
    }

    public void deleteByUsername(String username) {
        redisTemplate.delete(username);
    }

}
