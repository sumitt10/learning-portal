package com.effigoglobal.learningportal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigoglobal.learningportal.dtos.FavouriteResponseDto;
import com.effigoglobal.learningportal.entities.FavoriteEntity;
import com.effigoglobal.learningportal.services.FavouriteService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("learningportal/favorite-courses")
public class FavouriteController {

	private FavouriteService favouriteService;

	@GetMapping
	public ResponseEntity<Object> showAllCategories() {
		log.info("Showing all favorite courses !!");
		List<FavoriteEntity> favourites = favouriteService.findAllFavourites();
		if (favourites.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No favourites found!");
		}
		List<FavouriteResponseDto> favouritesresp = favourites.stream().map(favouriteService::mapFavouriteEntitytoDto)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(favouritesresp);
	}
}
