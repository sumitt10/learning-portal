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
	private EnrollmentRepository EnrollmentRepository;

	@Override
	public List<EnrollmentEntity> findAllEnrollments() {
		return EnrollmentRepository.findAll();
	}

	@Override
	public Optional<EnrollmentEntity> findEnrollmentById(Long id) {
		return EnrollmentRepository.findById(id);
	}

	@Override
	public void saveEnrollment(EnrollmentEntity enrollmentEntity) {
		EnrollmentRepository.save(enrollmentEntity);
	}

	@Override
	public boolean checkEnrollmentByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		EnrollmentEntity enrollmentEntity = EnrollmentRepository.findByUserEntityAndCourseEntity(userEntity,
				courseEntity);
		if (enrollmentEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<EnrollmentEntity> findEnrollmentByUserEntity(UserEntity userEntity) {
		return EnrollmentRepository.findByUserEntity(userEntity);
	}

	@Override
	public boolean checkEnrollmentByUser(UserEntity userEntity) {
		List<EnrollmentEntity> enrollments = EnrollmentRepository.findByUserEntity(userEntity);
		if (enrollments.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<CourseResponseDto> findEnrolledCoursesByUser(UserEntity userEntity) {
		List<EnrollmentEntity> enrollments = EnrollmentRepository.findByUserEntity(userEntity);
		List<CourseEntity> courses = enrollments.stream().map(enrollment -> enrollment.getCourseEntity())
				.collect(Collectors.toList());
		return courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList());
	}

	@Override
	public EnrollmentResponseDto mapEnrollmentEntitytoDto(EnrollmentEntity enrollmentEntity) {
		EnrollmentResponseDto enrollmentDto = new EnrollmentResponseDto();
		enrollmentDto.setId(enrollmentEntity.getId());
		enrollmentDto.setUserName(enrollmentEntity.getUserEntity().getName());
		enrollmentDto.setCourseName(enrollmentEntity.getCourseEntity().getName());
		return enrollmentDto;
	}

	@Override
	public void removeEnrollment(EnrollmentEntity enrollmentEntity) {
		EnrollmentRepository.delete(enrollmentEntity);

	}

	@Override
	public EnrollmentEntity getEnrollmentByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		return EnrollmentRepository.findByUserEntityAndCourseEntity(userEntity, courseEntity);
	}

	@Override
	public List<EnrollmentEntity> findEnrollmentByCourseEntity(CourseEntity courseEntity) {
		return EnrollmentRepository.findByCourseEntity(courseEntity);
	}

}
