package com.dei.survey.service;

import com.dei.survey.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> getAllCategories();

    public CategoryDTO getCategoryById(Long categoryId);

    public CategoryDTO createCategory(CategoryDTO categoryDTO);
}
