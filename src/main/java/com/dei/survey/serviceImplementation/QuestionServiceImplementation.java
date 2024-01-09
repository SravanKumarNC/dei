package com.dei.survey.serviceImplementation;

import com.dei.survey.dao.CategoryDAO;
import com.dei.survey.dao.QuestionDAO;
import com.dei.survey.dto.QuestionDTO;
import com.dei.survey.model.Question;
import com.dei.survey.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    public  QuestionDAO questionDAO;

    @Autowired
    public CategoryDAO categoryDAO;

    @Override
    public List<QuestionDTO> findQuestionsByCategoryId(Long categoryId) {
        List<Question> questions = questionDAO.findByCategoryCategoryId(categoryId);

        return questions.stream()
                .map(this :: convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO findByQuestionId(Long questionId) {
        Optional<Question> question = questionDAO.findById(questionId);
        return question.map(this :: convertToDTO).orElse(null);
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        Question savedQuestion = questionDAO.save(question);
        return convertToDTO(savedQuestion);
    }


    // CONVERTING ENTITY TO DTO
    private QuestionDTO convertToDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setCategoryId(question.getCategory().getCategoryId());

        return questionDTO;
    }

    //CONVERTING DTO TO ENTITY
    private Question convertToEntity(QuestionDTO questionDTO){
        Question question = new Question();

        question.setCategory(categoryDAO.findById(questionDTO.getCategoryId()).get());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setQuestionId(questionDTO.getQuestionId());
        return question;
    }
}
