package com.nojh.thinkit.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nojh.thinkit.auth.controller.AuthController;
import com.nojh.thinkit.auth.service.JoinService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@Log4j2
class AuthControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JoinService joinService;

    @Test
    @DisplayName("회원가입테스트")
    @WithMockUser
    void testJoin() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = Map.of("nickname", "asdasdasd"
                , "username", "asdadsasdad",
                "password", "123123123");
        given(joinService.join(any())).willReturn(-1);

        mvc.perform(post("/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("result", is("exist")));
    }
}