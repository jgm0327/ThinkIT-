package com.nojh.thinkit.auth.entity;

import com.nojh.thinkit.favorites.Entity.Keyword_users;
import com.nojh.thinkit.study.entity.Interview;
import com.nojh.thinkit.study.entity.Keyword;
import com.nojh.thinkit.study.entity.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 70)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false, length = 10)
    private String authority;

    @Column(nullable = false, updatable = false)
    private LocalDate regDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Keyword_users> keywordUsers;
}
