package com.effigoglobal.learningportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigoglobal.learningportal.entities.CategoryEntity;
import com.effigoglobal.learningportal.entities.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
	List<CourseEntity> findByCategoryEntity(CategoryEntity categoryEntity);

	CourseEntity findByAuthor(String author);
}
