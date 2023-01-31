package com.nojh.thinkit.study.service;

import com.nojh.thinkit.study.dto.ProblemDTO;

import java.util.List;

public interface StudyService {
    List<String> getKeywords(String subject_name);
    String getConcept(String keyword_name);

    ProblemDTO getProblems(String problem_name);

    List<String> getInterviewProblems(String interview_name);
}
