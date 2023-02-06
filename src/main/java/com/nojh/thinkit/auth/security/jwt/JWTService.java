package com.nojh.thinkit.auth.security.jwt;

import io.jsonwebtoken.JwtException;

import java.util.Map;

public interface JWTService {

    Map<String, Object> validateToken(String token) throws JwtException;

    String generateToken(Map<String, Object> claims, int day);
}
