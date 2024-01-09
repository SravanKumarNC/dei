package com.dei.survey.serviceImplementation;


import com.dei.survey.dao.CategoryDAO;
import com.dei.survey.dto.CategoryDTO;
import com.dei.survey.dto.QuestionDTO;
import com.dei.survey.model.Category;
import com.dei.survey.model.Question;
import com.dei.survey.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryDAO.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {

        Optional<Category> category = categoryDAO.findById(categoryId);

        return category.map(this::convertToDTO).orElse(null);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToCategoryEntity(categoryDTO);
        Category savedCategory = categoryDAO.save(category);
        return convertToDTO(savedCategory);
    }

    //CONVERTING ENTITY TO DTO

    private CategoryDTO convertToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());

        List<QuestionDTO> questionDTOS = category.getQuestions().stream()
                .map(question -> {
                    QuestionDTO questionDTO = new QuestionDTO();
                    questionDTO.setQuestionId(question.getQuestionId());
                    questionDTO.setQuestionText(question.getQuestionText());
                    questionDTO.setCategoryId(question.getCategory().getCategoryId());

                    return questionDTO;
                }).collect(Collectors.toList());
        categoryDTO.setQuestions(questionDTOS);
        return categoryDTO;
    }

    // CONVERTING CategoryDTO TO  CategoryEntity

    private Category convertToCategoryEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        List<Question> questions = categoryDTO.getQuestions().stream()
                .map(this :: convertToQuestionEntity)
                .collect(Collectors.toList());
        category.setQuestions(questions);

        return category;
    }

    // CONVERTING QuestionDTO TO QuestionEntity

    private Question convertToQuestionEntity(QuestionDTO questionDTO){
        Question question = new Question();
        question.setQuestionId(questionDTO.getQuestionId());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setCategory(categoryDAO.findById(questionDTO.getCategoryId()).get());
        return question;
    }
}
