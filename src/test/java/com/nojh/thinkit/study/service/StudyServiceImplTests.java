package com.nojh.thinkit.study.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}