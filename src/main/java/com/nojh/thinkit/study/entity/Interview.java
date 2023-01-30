package com.nojh.thinkit.study.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "subject")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String problems;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Subject subject;

}
