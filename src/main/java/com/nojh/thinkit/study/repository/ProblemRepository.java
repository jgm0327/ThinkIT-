package com.nojh.thinkit.study.repository;

import com.nojh.thinkit.study.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    @Query(value = "select * from problem where subject_id = :id order by rand() limit :cnt", nativeQuery = true)
    List<Problem> findBySubject_Id(int id, int cnt);
}
