package com.nojh.thinkit.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/mypage")
public class MyPageController {
    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("나이스");
    }
}
