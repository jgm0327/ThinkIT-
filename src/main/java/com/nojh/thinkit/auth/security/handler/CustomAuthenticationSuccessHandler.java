package com.nojh.thinkit.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nojh.thinkit.auth.security.jwt.JWTService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JWTService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Map<String, Object> claim = Map.of("username", authentication.getName());
        String accessToken = jwtService.generateToken(claim, 3);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> value = Map.of("accessToken", accessToken);
        String sendToken = objectMapper.writeValueAsString(value);
        response.getWriter().println(sendToken);
    }
}
