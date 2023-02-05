package com.nojh.thinkit.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserDetails {

    @NotNull
    @Size(min = 8, max = 20, message = "8~20 글자로 해주세요")
    private String username;

    @NotNull
    @Size(min = 8, max = 20, message = "8~20 글자로 해주세요")
    private String password;

    private String authority;

    @NotNull
    @Size(min = 8, max = 20, message = "8~20 글자로 해주세요")
    private String nickname;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private LocalDate regDate = LocalDate.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
