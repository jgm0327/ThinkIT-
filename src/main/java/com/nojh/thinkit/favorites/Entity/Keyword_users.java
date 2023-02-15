package com.nojh.thinkit.favorites.Entity;

import com.nojh.thinkit.auth.entity.User;
import com.nojh.thinkit.study.entity.Keyword;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Keyword_users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Keyword keyword;

    @ManyToOne
    private User user;
}
