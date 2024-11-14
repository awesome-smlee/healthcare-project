package com.insilicogen.healthcareproject.layer.domain.mapper;

import com.insilicogen.healthcareproject.layer.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findById(String id);
    void saveUserInfo(User user);
    boolean existsById(String id);
}
