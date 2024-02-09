package com.effigoglobal.learningportal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigoglobal.learningportal.dtos.CourseRequestDto;
import com.effigoglobal.learningportal.dtos.CourseResponseDto;
import com.effigoglobal.learningportal.entities.CategoryEntity;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.services.CategoryService;
import com.effigoglobal.learningportal.services.CourseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("learningportal/courses")
public class CourseController {

	private CourseService courseService;
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Object> showAllCourses() {
		log.info("Showing all courses !!");
		List<CourseEntity> courses = courseService.findAllCourse();

		if (courses != null && !courses.isEmpty()) {
			List<CourseResponseDto> courseresp = courses.stream().map(courseService::mapCourseEntitytoCourseDto)
					.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(courseresp);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found!!");
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCourse(@RequestBody CourseRequestDto courseRequestDto) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setName(courseRequestDto.getName());
		courseEntity.setAuthor(courseRequestDto.getAuthor());
		courseEntity.setDesc(courseRequestDto.getDesc());
		if (categoryService.findCategoryByName(courseRequestDto.getCategory()) != null) {
			courseEntity.setCategoryEntity(categoryService.findCategoryByName(courseRequestDto.getCategory()));
		} else {
			CategoryEntity categoryEntity = new CategoryEntity();
			categoryEntity.setName(courseRequestDto.getCategory());
			categoryService.addNewCategory(categoryEntity);
			courseEntity.setCategoryEntity(categoryService.findCategoryByName(courseRequestDto.getCategory()));
		}
		log.info(" Course added successfully !!");
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseEntity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCourse(@PathVariable(value = "id") Long id,
			@RequestBody CourseRequestDto courseRequestDto) {
		CourseEntity courseEntity = courseService.findCourseByAuthor(courseRequestDto.getAuthor());
		courseEntity.setName(courseRequestDto.getName());
		courseEntity.setDesc(courseRequestDto.getDesc());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.addCourse(courseEntity));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCourseById(@PathVariable(value = "id") Long id) {
		courseService.deleteCourseById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Course deleted !!");
	}

}
