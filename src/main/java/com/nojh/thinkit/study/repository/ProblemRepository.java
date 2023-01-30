package com.nojh.thinkit.study.repository;

import com.nojh.thinkit.study.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    
}
