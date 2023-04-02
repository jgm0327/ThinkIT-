package com.nojh.thinkit.study.repository;

import com.nojh.thinkit.study.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {

    @Query(value = "select * from Interview where subject_id = :id order by rand() limit :cnt", nativeQuery = true)
    List<Interview> getInterviewsBySubjectId(int id, int cnt);
}
