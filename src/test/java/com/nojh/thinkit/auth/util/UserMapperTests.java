package com.nojh.thinkit.auth.util;

import com.nojh.thinkit.auth.dto.UserDTO;
import com.nojh.thinkit.auth.entity.User;
import com.nojh.thinkit.auth.util.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class UserMapperTests {

    @Test
    @DisplayName("user 엔티티에서 DTO로 변환")
    void testMapping(){
        User user = User.builder()
                .username("asdadsasdad")
                .password("asdasdadasdasd")
                .nickname("asdasdasdada").build();
        log.info(UserMapper.INSTANCE.userToUserDTO(user));
    }

    @Test
    @DisplayName("user DTO에서 엔티티로 변환")
    void testUserDTOToUser(){
        UserDTO userDTO = UserDTO.builder()
                .username("asdadsasdad")
                .password("asdasdadasdasd")
                .nickname("asdasdasdada")
                .authority("ROLE_USER").build();
        log.info(UserMapper.INSTANCE.userDTOToUser(userDTO));
    }
}