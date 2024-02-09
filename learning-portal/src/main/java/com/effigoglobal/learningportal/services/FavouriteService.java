package com.effigoglobal.learningportal.services;

import java.util.List;
import java.util.Optional;

import com.effigoglobal.learningportal.dtos.FavouriteResponseDto;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.FavoriteEntity;
import com.effigoglobal.learningportal.entities.UserEntity;

public interface FavouriteService {
	public List<FavoriteEntity> findAllFavourites();

	public Optional<FavoriteEntity> findFavouriteById(Long id);

	public void saveFavourite(FavoriteEntity favouriteEntity);

	public List<FavoriteEntity> findFavouriteByUserEntity(UserEntity userEntity);

	public List<FavoriteEntity> findFavouriteByCourseEntity(CourseEntity courseEntity);

	public FavoriteEntity getFavouriteByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity);

	public boolean checkFavouriteByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity);

	public void removeFavourite(FavoriteEntity favouriteEntity);

	public FavouriteResponseDto mapFavouriteEntitytoDto(FavoriteEntity favouriteEntity);
}
