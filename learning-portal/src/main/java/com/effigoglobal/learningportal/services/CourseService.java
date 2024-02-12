package com.effigoglobal.learningportal.services;

import java.util.List;
import java.util.Optional;

import com.effigoglobal.learningportal.dtos.CourseResponseDto;
import com.effigoglobal.learningportal.entities.CategoryEntity;
import com.effigoglobal.learningportal.entities.CourseEntity;

public interface CourseService {
	public List<CourseEntity> findAllCourse();

	public Optional<CourseEntity> findCourseById(Long id);

	public CourseEntity addCourse(CourseEntity courseEntity);

	public List<CourseEntity> findCourseByCategory(CategoryEntity categoryEntity);

	public CourseEntity findCourseByAuthor(String author);

	public CourseResponseDto mapCourseEntitytoCourseDto(CourseEntity courseEntity);

	public void deleteCourseById(Long id);
}
