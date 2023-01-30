package com.nojh.thinkit.study.repository;

import com.nojh.thinkit.study.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer>{
}
