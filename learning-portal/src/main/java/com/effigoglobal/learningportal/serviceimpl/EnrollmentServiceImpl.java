package com.effigoglobal.learningportal.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.effigoglobal.learningportal.dtos.CourseResponseDto;
import com.effigoglobal.learningportal.dtos.EnrollmentResponseDto;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.EnrollmentEntity;
import com.effigoglobal.learningportal.entities.UserEntity;
import com.effigoglobal.learningportal.repository.EnrollmentRepository;
import com.effigoglobal.learningportal.services.CourseService;
import com.effigoglobal.learningportal.services.EnrollmentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private CourseService courseService;
	private EnrollmentRepository enrollmentRepository;

	@Override
	public List<EnrollmentEntity> findAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	@Override
	public Optional<EnrollmentEntity> findEnrollmentById(Long id) {
		return enrollmentRepository.findById(id);
	}

	@Override
	public void saveEnrollment(EnrollmentEntity enrollmentEntity) {
		enrollmentRepository.save(enrollmentEntity);
	}

	@Override
	public boolean checkEnrollmentByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUserEntityAndCourseEntity(userEntity,
				courseEntity);
		if (enrollmentEntity == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<EnrollmentEntity> findEnrollmentByUserEntity(UserEntity userEntity) {
		return enrollmentRepository.findByUserEntity(userEntity);
	}

	@Override
	public boolean checkEnrollmentByUser(UserEntity userEntity) {
		List<EnrollmentEntity> enrollments = enrollmentRepository.findByUserEntity(userEntity);
		if (enrollments.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public List<CourseResponseDto> findEnrolledCoursesByUser(UserEntity userEntity) {
		List<EnrollmentEntity> enrollments = enrollmentRepository.findByUserEntity(userEntity);
		List<CourseEntity> courses = enrollments.stream().map(enrollment -> enrollment.getCourseEntity())
				.collect(Collectors.toList());
		return courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList());
	}

	public EnrollmentResponseDto mapEnrollmentEntitytoDto(EnrollmentEntity enrollmentEntity) {
		EnrollmentResponseDto dto = new EnrollmentResponseDto();

		dto.setId(enrollmentEntity.getUserEntity().getId());
		dto.setUserName(enrollmentEntity.getUserEntity().getName());

		CourseEntity courseEntity = enrollmentEntity.getCourseEntity();
		if (courseEntity != null) {
			dto.setCourseName(courseEntity.getName());
		} else {
			dto.setCourseName("No Course Enrolled");
		}

		return dto;
	}

	@Override
	public void removeEnrollment(EnrollmentEntity enrollmentEntity) {
		enrollmentRepository.delete(enrollmentEntity);

	}

	@Override
	public EnrollmentEntity getEnrollmentByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		return enrollmentRepository.findByUserEntityAndCourseEntity(userEntity, courseEntity);
	}

	@Override
	public List<EnrollmentEntity> findEnrollmentByCourseEntity(CourseEntity courseEntity) {
		return enrollmentRepository.findByCourseEntity(courseEntity);
	}

}
