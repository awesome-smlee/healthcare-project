package com.insilicogen.healthcareproject.layer.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@RedisHash("User")
@Builder
//@RedisHash(value = "User", timeToLive = 30)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
//    private String name;
    private String password;
//    private int age;
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
