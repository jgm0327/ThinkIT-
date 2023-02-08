package com.nojh.thinkit.study.controller;

import com.nojh.thinkit.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class StudyViewController {

    private final StudyService studyService;

    @GetMapping("/view/concepts/{keyword}")
    public String getViewConcepts(@PathVariable("keyword") String keyword, Model model){
        log.info(keyword);
        String result = studyService.getConcept(keyword);
        List<String> concepts = Arrays.asList(result.split("\\."));
        model.addAttribute("keyword", keyword);
        model.addAttribute("concepts", concepts);
        return "/view/concepts";
    }
}
