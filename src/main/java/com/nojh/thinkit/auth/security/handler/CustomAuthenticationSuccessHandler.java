package com.nojh.thinkit.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nojh.thinkit.auth.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Map<String, Object> claim = Map.of("username", username);
        String accessToken = jwtService.generateToken(claim, 3);
        ObjectMapper objectMapper = new ObjectMapper();
        String nickName = userRepository
                .findByUsername(username)
                .orElseThrow().getNickname();
        Map<String, String> value = Map.of("accessToken", accessToken,
                "nickname", nickName,
                "result", "success");
        String sendToken = objectMapper.writeValueAsString(value);
        response.getWriter().println(sendToken);
    }
}
