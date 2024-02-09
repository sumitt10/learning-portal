package com.effigoglobal.learningportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.FavoriteEntity;
import com.effigoglobal.learningportal.entities.UserEntity;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
	public FavoriteEntity findByUserFavEntityAndCourseFavEntity(UserEntity userEntity, CourseEntity courseEntity);

	public List<FavoriteEntity> findByUserFavEntity(UserEntity userEntity);

	public List<FavoriteEntity> findByCourseFavEntity(CourseEntity courseEntity);
}
