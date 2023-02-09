package com.nojh.thinkit.auth.security.config;

import com.nojh.thinkit.auth.repository.UserRepository;
import com.nojh.thinkit.auth.security.filter.CustomAuthenticationFilter;
import com.nojh.thinkit.auth.security.filter.JWTFilter;
import com.nojh.thinkit.auth.security.handler.CustomAuthenticationFailureHandler;
import com.nojh.thinkit.auth.security.handler.CustomAuthenticationSuccessHandler;
import com.nojh.thinkit.auth.security.jwt.JWTService;
import com.nojh.thinkit.auth.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@Log4j2
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JWTService jwtService;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //AuthenticationManager 생성하기
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);

        http.httpBasic().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/mypage/**").authenticated();
            auth.requestMatchers("/**").permitAll();
        });

        // 커스텀한 인증 필터 생성
        CustomAuthenticationFilter customAuthentication =
                new CustomAuthenticationFilter("/login/**");
        customAuthentication.setAuthenticationManager(authenticationManager);
        customAuthentication.setAuthenticationSuccessHandler(
                new CustomAuthenticationSuccessHandler(jwtService, userRepository));
        customAuthentication.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());

        http.addFilterBefore(customAuthentication, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtFilter(jwtService, userDetailsService),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private JWTFilter jwtFilter(JWTService jwtService, CustomUserDetailsService userDetailsService) {
        return new JWTFilter(jwtService, userDetailsService);
    }

}
