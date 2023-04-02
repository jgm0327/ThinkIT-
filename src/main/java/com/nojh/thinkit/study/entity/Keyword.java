package com.nojh.thinkit.study.entity;

import com.nojh.thinkit.auth.entity.User;
import com.nojh.thinkit.favorites.Entity.Keyword_users;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "subject")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "keyword")
    private List<Concept> concepts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Subject subject;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "keyword" , orphanRemoval = true)
    private List<Keyword_users> keywordUsers;
}
