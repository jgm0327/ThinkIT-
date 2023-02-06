package com.nojh.thinkit.auth.security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class JWTServiceImpl implements JWTService {

    @Value("${com.nojh.token.secret-key}")
    private String SECRET_KEY;

    @Override
    public Map<String, Object> validateToken(String token) throws JwtException {
        log.info(token);
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateToken(Map<String, Object> claims, int day) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        Map<String, Object> payload = new HashMap<>(claims);
        int expiration_time = 60 * 60 * 3;
        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payload)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now()
                        .plusMinutes(expiration_time).toInstant()))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
