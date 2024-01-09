package com.dei.survey.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String description;
    private List<QuestionDTO> questions = new ArrayList<>();
}
