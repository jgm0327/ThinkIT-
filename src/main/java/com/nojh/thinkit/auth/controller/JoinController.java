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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class JoinController {
    private final JoinService joinService;

    @PreAuthorize("permitAll")
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> join(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        // 회원가입 정보가 정해진 규칙을 따르지 않으면 fail
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("result", "fail"));
        }
        // 이미 존재하는 username을 쓰면 존재한다는 exist 값 전달
        if (joinService.join(userDTO) == -1) {
            return ResponseEntity.badRequest().body(Map.of("result", "exist"));
        }
        // 회원가입이 정상적으로
        return ResponseEntity.ok(Map.of("result", "success"));
    }

}
