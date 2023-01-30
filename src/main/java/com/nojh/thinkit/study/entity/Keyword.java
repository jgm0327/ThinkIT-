package com.nojh.thinkit.study.entity;

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

    @ManyToOne
    private Subject subject;
}
