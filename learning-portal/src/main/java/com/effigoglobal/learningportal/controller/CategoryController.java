package com.effigoglobal.learningportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigoglobal.learningportal.entities.CategoryEntity;
import com.effigoglobal.learningportal.services.CategoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("learningportal/categories")
public class CategoryController {

	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Object> showAllCategories() {
		log.info("showing all categories !!");
		List<CategoryEntity> categories = categoryService.findAllCategories();
		if (categories.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(categories);

	}

}
