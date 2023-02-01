package com.nojh.thinkit.study.service;

import com.nojh.thinkit.study.dto.ProblemDTO;

import java.util.List;

public interface StudyService {
    List<String> getKeywords(String subject_name);
    String getConcept(String keyword_name);

    ProblemDTO getProblems(List<String> subjects);

    List<String> getInterviewProblems(List<String> interview_name);
}
