package com.nojh.thinkit.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudyController {

    @GetMapping("/keywords/{subject}")
    public ResponseEntity<Map<String, List<String>>> sendKeywords(@PathVariable("subject") String subject) {
        return ResponseEntity.ok(Map.of("result", List.of("success")));
    }

    @GetMapping("/concepts/{keyword}")
    public ResponseEntity<Map<String, String>> sendConcept(@PathVariable("keyword") List<String> subjects) {
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    @GetMapping("/problems")
    public ResponseEntity<Map<String, List<String>>> sendProblems(@RequestParam("subjects") List<String> subjects) {
        return ResponseEntity.ok(Map.of("result", List.of("success")));
    }

    @GetMapping("/interviews")
    public ResponseEntity<Map<String, List<String>>> sendKeywords(@RequestParam("subjects") List<String> subjects) {
        return ResponseEntity.ok(Map.of("result", List.of("success")));
    }
}
