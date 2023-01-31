package com.nojh.thinkit.study.controller;

import com.nojh.thinkit.study.service.StudyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({StudyController.class})
@Log4j2
class StudyControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private StudyService studyService;

    @Test
    void testGETKeyword() throws Exception {
        Map<String, List<String>> result = Map.of("keywords", Arrays.asList("배열", "연결리스트", "스택", "큐",
                "트리", "힙", "해시테이블", "그래프"));
        List<String> a = Arrays.asList("배열", "연결리스트", "스택", "큐",
                "트리", "힙", "해시테이블", "그래프");

        given(studyService.getKeywords("자료구조")).willReturn(a);
        mvc.perform(get("/api/keywords/자료구조")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keywords", is(a)));
    }
}