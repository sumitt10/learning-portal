package com.effigoglobal.learningportal.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.effigoglobal.learningportal.dtos.FavouriteResponseDto;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.FavoriteEntity;
import com.effigoglobal.learningportal.entities.UserEntity;
import com.effigoglobal.learningportal.repository.FavoriteRepository;
import com.effigoglobal.learningportal.services.FavouriteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FavouriteServiceImpl implements FavouriteService {

	private FavoriteRepository favouriteRepository;

	@Override
	public List<FavoriteEntity> findAllFavourites() {
		return favouriteRepository.findAll();
	}

	@Override
	public Optional<FavoriteEntity> findFavouriteById(Long id) {
		return favouriteRepository.findById(id);
	}

	@Override
	public void saveFavourite(FavoriteEntity favouriteEntity) {
		favouriteRepository.save(favouriteEntity);
	}

	@Override
	public List<FavoriteEntity> findFavouriteByUserEntity(UserEntity userEntity) {
		return favouriteRepository.findByUserFavEntity(userEntity);
	}

	@Override
	public List<FavoriteEntity> findFavouriteByCourseEntity(CourseEntity courseEntity) {
		return favouriteRepository.findByCourseFavEntity(courseEntity);
	}

	@Override
	public FavoriteEntity getFavouriteByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		return favouriteRepository.findByUserFavEntityAndCourseFavEntity(userEntity, courseEntity);
	}

	@Override
	public boolean checkFavouriteByUserAndCourse(UserEntity userEntity, CourseEntity courseEntity) {
		FavoriteEntity favourite = favouriteRepository.findByUserFavEntityAndCourseFavEntity(userEntity, courseEntity);
		if (favourite == null) {
			return false;
		}
		return true;
	}

	@Override
	public void removeFavourite(FavoriteEntity favouriteEntity) {
		favouriteRepository.delete(favouriteEntity);
	}

	@Override
	public FavouriteResponseDto mapFavouriteEntitytoDto(FavoriteEntity favouriteEntity) {
		FavouriteResponseDto favouriteDto = new FavouriteResponseDto();
		favouriteDto.setId(favouriteEntity.getId());
		favouriteDto.setUserName(favouriteEntity.getUserFavEntity().getName());
		favouriteDto.setCourseName(favouriteEntity.getCourseFavEntity().getName());
		return favouriteDto;
	}

}
