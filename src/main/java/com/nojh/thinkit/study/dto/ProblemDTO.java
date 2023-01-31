package com.nojh.thinkit.study.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProblemDTO {
    private List<String> titles;
    private List<String> selects;
}
