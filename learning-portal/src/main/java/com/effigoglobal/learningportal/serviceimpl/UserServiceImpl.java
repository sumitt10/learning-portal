package com.effigoglobal.learningportal.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.effigoglobal.learningportal.dtos.UserResponseDto;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.UserEntity;
import com.effigoglobal.learningportal.repository.UserRepository;
import com.effigoglobal.learningportal.services.CourseService;
import com.effigoglobal.learningportal.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private CourseService courseService;
	private UserRepository userRepository;

	@Override
	public List<UserEntity> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserEntity addUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserResponseDto userEntitytoDtoMapper(UserEntity userEntity) {
		UserResponseDto userDto = new UserResponseDto();
		userDto.setId(userEntity.getId());
		userDto.setName(userEntity.getName());
		userDto.setRole(userEntity.getRole());
		if (userEntity.getEnrolledCourses() != null) {
			userDto.setEnrolledCourses(userEntity.getEnrolledCourses().stream()
					.map(registration -> courseService.mapCourseEntitytoCourseDto(registration.getCourseEntity()))
					.collect(Collectors.toList()));
		}
		if (userEntity.getFavoriteCourses() != null) {
			List<CourseEntity> courses = userEntity.getFavoriteCourses().stream().map(pred -> pred.getCourseFavEntity())
					.collect(Collectors.toList());
			userDto.setFavoriteCourses(
					courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList()));
		}
		return userDto;
	}

	@Override
	public void removeUserById(Long id) {
		userRepository.deleteById(id);
	}

}
