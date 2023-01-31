package com.nojh.thinkit.study.service;

import com.nojh.thinkit.study.dto.ProblemDTO;
import com.nojh.thinkit.study.entity.Keyword;
import com.nojh.thinkit.study.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {
    private final SubjectRepository subjectRepository;
    private final ProblemRepository problemRepository;
    private final ConceptRepository conceptRepository;
    private final InterviewRepository interviewRepository;
    private final KeywordRepository keywordRepository;

    @Override
    public List<String> getKeywords(String subject_name) {
        int subject_id = subjectRepository.findByName(subject_name).orElseThrow().getId();
        return keywordRepository
                .findAllBySubject_Id(subject_id)
                .stream().map(Keyword::getName).toList();
    }

    @Override
    public String getConcept(String keyword_name) {
        return null;
    }

    @Override
    public ProblemDTO getProblems(String problem_name) {
        return null;
    }

    @Override
    public List<String> getInterviewProblems(String interview_name) {
        return null;
    }
}
