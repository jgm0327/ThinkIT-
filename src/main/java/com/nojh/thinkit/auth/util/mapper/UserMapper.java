package com.nojh.thinkit.auth.util.mapper;

import com.nojh.thinkit.auth.dto.UserDTO;
import com.nojh.thinkit.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "authority", target = "authority")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "regDate", target = "regDate")
    @Mapping(source = "enabled", target = "enabled")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "authority", target = "authority")
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "regDate", target = "regDate")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(target = "id", ignore = true)
    User userDTOToUser(UserDTO userDTO);

}
