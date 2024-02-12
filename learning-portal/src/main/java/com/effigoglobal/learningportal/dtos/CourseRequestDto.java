package com.effigoglobal.learningportal.dtos;

import lombok.Data;

@Data
public class CourseRequestDto {
	private String name;
	private String author;
	private String desc;
	private String category;
}
