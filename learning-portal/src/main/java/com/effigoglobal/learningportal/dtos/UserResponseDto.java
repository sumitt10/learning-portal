package com.effigoglobal.learningportal.dtos;

import java.util.Date;
import java.util.List;

import com.effigoglobal.learningportal.entities.UserEntity;

import lombok.Data;

@Data
public class UserResponseDto {

	private Long id;
	private String name;
	private UserEntity.Roles role;
	private List<CourseResponseDto> enrolledCourses;
	private List<CourseResponseDto> favoriteCourses;
	private Date createdDate;
}
