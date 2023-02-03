package com.nojh.thinkit.auth.entity;

import com.nojh.thinkit.study.entity.Concept;
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

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false, length = 10)
    private String authority;

    @ManyToOne(targetEntity = Keyword.class)
    private Keyword keyword_favorites;

    @ManyToOne(targetEntity = Interview.class)
    private Interview interview_favorites;

    @ManyToOne(targetEntity = Problem.class)
    private Problem problem;

    @Column(nullable = false, updatable = false)
    private LocalDate regDate;
}
