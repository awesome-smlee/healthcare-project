package com.insilicogen.healthcareproject.layer.application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long EXPIRATION_MS = 86400000; // 24시간


    // 토큰 생성
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + EXPIRATION_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SECRET_KEY)
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 유저 정보 추출
    public String getUsername(String token) {
        return  Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
    }

    // 만료 시간 반환
    public long getExpirationMs() {
        return EXPIRATION_MS;
    }

}
