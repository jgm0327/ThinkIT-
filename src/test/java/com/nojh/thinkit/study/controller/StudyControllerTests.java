package com.nojh.thinkit.study.controller;

import com.nojh.thinkit.study.repository.ConceptRepository;
import com.nojh.thinkit.study.repository.KeywordRepository;
import com.nojh.thinkit.study.service.StudyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Log4j2
class StudyControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private StudyService studyService;


    @Test
    void testGETKeyword() throws Exception {
        List<String> a = Arrays.asList("배열", "연결리스트", "스택", "큐",
                "트리", "힙", "해시테이블", "그래프");

        given(studyService.getKeywords("자료구조")).willReturn(a);
        mvc.perform(get("/api/keywords/자료구조")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keywords", is(a)));
    }

    @Test
    @DisplayName("개념 받아오는 테스트")
    void testGETConcept() throws Exception {
        String result = "배열은 연속된 메모리 공간에서 순차적으로 저장된 데이터의 모음의 선형 자료구조이다. " +
                "인덱스로 데이터에 접근하며 0부터 시작한다. " +
                "원하는 데이터는 인덱스로 접근할 수 있기 때문에 시간 복잡도는 O(1) 이다." +
                "데이터를 삽입, 삭제하기 위해서는 기존에 있던 데이터들을 " +
                "앞 또는 뒤로 한 칸씩 이동시켜야 하기때문에 시간 복잡도는 O(n)이다." +
                "장점으로는 구현이 쉽고 참조를 위한 추가적인 메모리가 필요하지 않는다." +
                "연속된 메모리 공간에 저장하기 때문에 메모리 관리가 편하다." +
                "인덱스를 통해 데이터에 접근하므로 검색이 빠르다." +
                "단점으로는 배열의 크기를 변경하려면 새로운 배열을 만들어서 기존의 데이터를 넣어야 한다." +
                "배열은 선언할 때 크기를 정하기 때문에 사용하지 않는 공간으로 인해 메모리 낭비가 발생할 수 있다.";
        given(studyService.getConcept("배열")).willReturn(result);
        mvc.perform(get("/api/concepts/배열")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyword_concept", is(result)))
                .andDo(print());
    }
}