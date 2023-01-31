package com.nojh.thinkit.study.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudyController.class)
@Log4j2
class StudyControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    void testGETKeyword() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/keywords/자료구조"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value("success"));
    }
}