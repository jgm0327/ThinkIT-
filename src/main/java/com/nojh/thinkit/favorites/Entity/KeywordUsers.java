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
@Table(indexes = {
        @Index(name = "idx_user_keyword", columnList = "user_id")
})
public class KeywordUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
