package com.effigoglobal.learningportal.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.effigoglobal.learningportal.entities.CategoryEntity;
import com.effigoglobal.learningportal.repository.CategoryRepository;
import com.effigoglobal.learningportal.services.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryEntity> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public CategoryEntity findCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public CategoryEntity addNewCategory(CategoryEntity categoryEntity) {
		return categoryRepository.save(categoryEntity);
	}

	@Override
	public Optional<CategoryEntity> findCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

}
