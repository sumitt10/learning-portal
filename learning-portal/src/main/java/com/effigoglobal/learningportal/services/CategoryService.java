package com.effigoglobal.learningportal.services;

import java.util.List;
import java.util.Optional;

import com.effigoglobal.learningportal.entities.CategoryEntity;

public interface CategoryService {
	public List<CategoryEntity> findAllCategories();

	public CategoryEntity findCategoryByName(String name);

	public CategoryEntity addNewCategory(CategoryEntity categoryEntity);

	public Optional<CategoryEntity> findCategoryById(Long id);
}
