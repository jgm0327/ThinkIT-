package com.nojh.thinkit.study.controller;

import com.nojh.thinkit.study.service.StudyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(controllers = StudyViewController.class)
@Log4j2
class StudyViewControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private StudyService studyService;

    @Test
    @DisplayName("개념 설명 화면 출력")
    void testGETConceptView() throws Exception{
        given(studyService.getConcept("힙")).willReturn("asdasd");
        assertNotNull(mvc.perform(get("/view/concepts").queryParam("keyword", "힙"))
                .andDo(print())
                .andReturn().getModelAndView().getModel().get("keyword"));

    }
}