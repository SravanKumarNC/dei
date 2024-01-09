package com.dei.survey.controller;

import com.dei.survey.dao.QuestionDAO;
import com.dei.survey.dto.QuestionDTO;
import com.dei.survey.model.Question;
import com.dei.survey.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    public QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestionsByCategory(Long categoryId){
        List<QuestionDTO> questions = questionService.findQuestionsByCategoryId(categoryId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long categoryId){
        QuestionDTO question = questionService.findByQuestionId(categoryId);
        return question != null ? ResponseEntity.ok(question) :ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO){
        QuestionDTO question = questionService.createQuestion(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }
}
