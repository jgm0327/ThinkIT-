package com.nojh.thinkit.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"problems", "interviews", "keywords"})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(orphanRemoval = true, mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Interview> interviews;

    @OneToMany(orphanRemoval = true, mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Keyword> keywords;

    @OneToMany(orphanRemoval = true, mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Problem> problems;


}
