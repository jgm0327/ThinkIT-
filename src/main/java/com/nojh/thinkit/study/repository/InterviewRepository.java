package com.nojh.thinkit.study.repository;

import com.nojh.thinkit.study.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
}
