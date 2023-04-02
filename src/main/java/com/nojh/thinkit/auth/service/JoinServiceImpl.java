package com.nojh.thinkit.auth.service;

import com.nojh.thinkit.auth.dto.UserDTO;
import com.nojh.thinkit.auth.entity.User;
import com.nojh.thinkit.auth.repository.UserRepository;
import com.nojh.thinkit.auth.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int join(UserDTO userDTO) {
        Optional<User> existed_user = userRepository.findByUsername(userDTO.getUsername());
        userDTO.setAuthority("ROLE_USER");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (existed_user.isPresent()) {
            return -1;
        }
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        return userRepository.save(user).getId();
    }
}
