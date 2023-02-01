package com.nojh.thinkit.study.service;

import com.nojh.thinkit.study.dto.ProblemDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class StudyServiceImplTests {

    @Autowired
    private StudyService studyService;

    @Test
    @DisplayName("원하는 과목의 키워드 받아오기")
    void testGetKeywords(){
        studyService.getKeywords("알고리즘").forEach(log::info);
    }

    @Test
    @DisplayName("원하는 키워드의 개념 받아오기")
    void testGetConcept(){
        String result = studyService.getConcept("힙");
        assertNotNull(result);
        log.info(result);
    }

    @Test
    @DisplayName("객관식 문제 받아오기")
    void testGETProblems(){
        ProblemDTO problemDTO = studyService.getProblems(List.of("자료구조", "알고리즘"));
        problemDTO.getTitles().forEach(log::info);
        problemDTO.getSelects().forEach(log::info);
    }

    @Test
    @DisplayName("면접 문제 받아오기")
    void testGETInterviews(){
        studyService.getInterviewProblems(List.of("인성", "CS")).forEach(log::info);
    }
}