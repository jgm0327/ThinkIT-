package com.nojh.thinkit.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@Log4j2
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public CustomAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        log.info("들어옴?");
        Map<String, String> jsonData = parseRequest(request);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                  jsonData.get("username"),
                  jsonData.get("password")
                );
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private Map<String, String> parseRequest(HttpServletRequest request){
        try(Reader reader = new InputStreamReader(request.getInputStream())){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, Map.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
