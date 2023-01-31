package com.nojh.thinkit.study.repository;

import com.nojh.thinkit.study.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptRepository extends JpaRepository<Concept, Integer> {
    Concept findByKeyword_Id(int keyword_id);
}
