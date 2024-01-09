package com.dei.survey.dto;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class QuestionDTO {

    private Long questionId;
    private String questionText;
    private Long categoryId;
}
