package com.dei.survey.dto;

import lombok.Data;

@Data
public class ScoreDTO {

    private Long scoreId;
    private Integer scoreValue;
    private Long categoryId;
    private Long questionId;
}
