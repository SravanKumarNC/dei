package com.dei.survey.service;

import com.dei.survey.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    public List<QuestionDTO> findQuestionsByCategoryId(Long categoryId);

    public QuestionDTO findByQuestionId(Long questionId);

    public QuestionDTO createQuestion(QuestionDTO questionDTO);
}
