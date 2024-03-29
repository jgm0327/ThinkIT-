package com.nojh.thinkit.study.controller;

import com.nojh.thinkit.study.dto.ProblemDTO;
import com.nojh.thinkit.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class StudyController {
    private final StudyService studyService;

    @GetMapping(value = "/keywords/{subject}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<String>>> sendKeywords(@PathVariable("subject") String subject) {
        List<String> keywords = studyService.getKeywords(subject);
        return ResponseEntity.ok(Map.of("keywords", keywords));
    }

    @GetMapping(value = "/concepts/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> sendConcept(@PathVariable("keyword") String keyword) {
        String concept = studyService.getConcept(keyword);
        return ResponseEntity.ok(Map.of("keyword_concept", concept));
    }

    @GetMapping("/problems")
    public ResponseEntity<ProblemDTO> sendProblems(@RequestParam("subjects") List<String> subjects) {
        ProblemDTO problemDTO = studyService.getProblems(subjects);
        return ResponseEntity.ok(problemDTO);
    }

    @GetMapping("/interviews")
    public ResponseEntity<Map<String, List<String>>> sendKeywords(@RequestParam("subjects") List<String> subjects) {
        List<String> problems = studyService.getInterviewProblems(subjects);
        return ResponseEntity.ok(Map.of("problmes", problems));
    }
}
