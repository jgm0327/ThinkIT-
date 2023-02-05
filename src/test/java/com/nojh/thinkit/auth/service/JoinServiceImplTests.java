package com.nojh.thinkit.auth.service;

import com.nojh.thinkit.auth.dto.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class JoinServiceImplTests {
    @Autowired
    private JoinService joinService;

    @Test
    @DisplayName("회원가입 서비스 확인")
    void testJoin(){
        assertEquals(joinService.join(UserDTO.builder()
                .username("testusername")
                .password("123123123")
                .nickname("tester12")
                .authority("ROLE_USER").build()), 5);
    }

}