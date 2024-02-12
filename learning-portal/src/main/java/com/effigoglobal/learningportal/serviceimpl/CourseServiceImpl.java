package com.effigoglobal.learningportal.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.effigoglobal.learningportal.dtos.CourseResponseDto;
import com.effigoglobal.learningportal.entities.CategoryEntity;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.repository.CourseRepository;
import com.effigoglobal.learningportal.services.CourseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	@Override
	public List<CourseEntity> findAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<CourseEntity> findCourseById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	public CourseEntity addCourse(CourseEntity courseEntity) {
		return courseRepository.save(courseEntity);
	}

	@Override
	public List<CourseEntity> findCourseByCategory(CategoryEntity categoryEntity) {
		return courseRepository.findByCategoryEntity(categoryEntity);
	}

	@Override
	public CourseEntity findCourseByAuthor(String author) {
		return courseRepository.findByAuthor(author);
	}

	@Override
	public CourseResponseDto mapCourseEntitytoCourseDto(CourseEntity courseEntity) {
		CourseResponseDto course = new CourseResponseDto();
		if (courseEntity == null) {
			return null;
		}
		course.setId(courseEntity.getId());
		course.setName(courseEntity.getName());
		course.setAuthor(courseEntity.getAuthor());
		course.setCategory(courseEntity.getCategoryEntity().getName());
		course.setDesc(courseEntity.getDesc());
		course.setEnrolledUsers(courseEntity.getEnrolledUsers().stream().map(pred -> pred.getUserEntity().getName())
				.collect(Collectors.toList()));
		course.setEnrolledUserCount(course.getEnrolledUsers().size());
		return course;
	}

	@Override
	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}
}
