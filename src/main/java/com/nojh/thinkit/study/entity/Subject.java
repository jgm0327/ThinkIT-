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

    private String name;

    @OneToMany(orphanRemoval = true, mappedBy = "subject")
    private List<Interview> interviews;

    @OneToMany(orphanRemoval = true, mappedBy = "subject")
    private List<Keyword> keywords;

    @OneToMany(orphanRemoval = true, mappedBy = "subject")
    private List<Problem> problems;


}
