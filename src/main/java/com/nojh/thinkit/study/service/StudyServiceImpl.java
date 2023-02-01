package com.nojh.thinkit.study.service;

import com.nojh.thinkit.study.dto.ProblemDTO;
import com.nojh.thinkit.study.entity.Keyword;
import com.nojh.thinkit.study.entity.Problem;
import com.nojh.thinkit.study.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return keywordRepository.findAllBySubject_Id(subject_id)
                .stream()
                .map(Keyword::getName).toList();
    }

    @Override
    public String getConcept(String keyword_name) {
        int keyword_id = keywordRepository.findByName(keyword_name).getId();
        return conceptRepository.findByKeyword_Id(keyword_id).getContent();
    }

    @Override
    public ProblemDTO getProblems(List<String> subjects) {
        List<String> titles = new ArrayList<>();
        List<String> selections = new ArrayList<>();
        int per_cnt = (10 / subjects.size()) + 1;
        subjects.forEach(subject ->{
            int id = subjectRepository.findByName(subject).orElseThrow().getId();
            problemRepository.findBySubject_Id(id, per_cnt).forEach(problem -> {
                titles.add(problem.getTitle());
                selections.add(problem.getSelection());
            });
        });
        return ProblemDTO.builder().titles(titles).selects(selections).build();
    }

    @Override
    public List<String> getInterviewProblems(String interview_name) {
        return null;
    }
}
