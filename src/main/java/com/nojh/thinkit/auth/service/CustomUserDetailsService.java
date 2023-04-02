package com.nojh.thinkit.auth.service;

import com.nojh.thinkit.auth.dto.UserDTO;
import com.nojh.thinkit.auth.entity.User;
import com.nojh.thinkit.auth.repository.UserRepository;
import com.nojh.thinkit.auth.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }
}
