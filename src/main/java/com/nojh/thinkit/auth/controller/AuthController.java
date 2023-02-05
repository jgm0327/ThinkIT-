package com.nojh.thinkit.auth.controller;

import com.nojh.thinkit.auth.dto.UserDTO;
import com.nojh.thinkit.auth.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthController {
    private final JoinService joinService;

    @PreAuthorize("permitAll")
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> join(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("result", "fail"));
        }
        if(joinService.join(userDTO) == -1){
            return ResponseEntity.badRequest().body(Map.of("result", "exist"));
        }
        log.info(userDTO);
        return ResponseEntity.ok(Map.of("result", "success"));
    }
}
