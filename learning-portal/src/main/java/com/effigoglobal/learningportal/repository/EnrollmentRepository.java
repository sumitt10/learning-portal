package com.effigoglobal.learningportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.EnrollmentEntity;
import com.effigoglobal.learningportal.entities.UserEntity;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
	public EnrollmentEntity findByUserEntityAndCourseEntity(UserEntity userEntity, CourseEntity courseEntity);

	public List<EnrollmentEntity> findByUserEntity(UserEntity userEntity);

	public List<EnrollmentEntity> findByCourseEntity(CourseEntity courseEntity);
}
