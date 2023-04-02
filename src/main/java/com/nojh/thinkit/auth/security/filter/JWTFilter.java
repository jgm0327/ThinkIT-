package com.nojh.thinkit.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nojh.thinkit.auth.security.jwt.JWTService;
import com.nojh.thinkit.auth.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;


@RequiredArgsConstructor
@Log4j2

public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        if (!request.getRequestURI().startsWith("/auth") && !request.getRequestURI().startsWith("/login")) {

            filterChain.doFilter(request, response);
            return;
        }
        try {
            Map<String, Object> claims = validate(request);
            String username = (String) claims.get("username");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            null,
                            userDetails.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }


    private Map<String, Object> validate(HttpServletRequest request) throws Exception {
        Map<String, Object> jwt = parseRequest(request);
        try {
            return jwtService.validateToken((String) jwt.get("accessToken"));
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("형식 이상");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("형식 싫음");
        } catch (ExpiredJwtException e) {
            throw new JwtException("기간 끝남");

        } catch (NullPointerException e) {
            throw new NullPointerException("없음");
        }
    }

    private Map<String, Object> parseRequest(HttpServletRequest request) {
        try (Reader reader = new InputStreamReader(request.getInputStream())) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
