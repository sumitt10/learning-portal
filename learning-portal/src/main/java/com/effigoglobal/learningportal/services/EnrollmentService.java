package com.effigoglobal.learningportal.services;

import java.util.List;
import java.util.Optional;

import com.effigoglobal.learningportal.dtos.CourseResponseDto;
import com.effigoglobal.learningportal.dtos.EnrollmentResponseDto;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.EnrollmentEntity;
import com.effigoglobal.learningportal.entities.UserEntity;

public interface EnrollmentService {
	public List<EnrollmentEntity> findAllEnrollments();

	public Optional<EnrollmentEntity> findEnrollmentById(Long id);

	public void saveEnrollment(EnrollmentEntity enrollmentEntity);

	public boolean checkEnrollmentByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity);

	public boolean checkEnrollmentByUser(UserEntity userEntity);

	public List<EnrollmentEntity> findEnrollmentByUserEntity(UserEntity userEntity);

	public List<EnrollmentEntity> findEnrollmentByCourseEntity(CourseEntity courseEntity);

	public List<CourseResponseDto> findEnrolledCoursesByUser(UserEntity userEntity);

	public EnrollmentResponseDto mapEnrollmentEntitytoDto(EnrollmentEntity enrollmentEntity);

	public void removeEnrollment(EnrollmentEntity enrollmentEntity);

	public EnrollmentEntity getEnrollmentByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity);
}
