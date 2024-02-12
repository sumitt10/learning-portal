package com.effigoglobal.learningportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigoglobal.learningportal.dtos.UserRequestDto;
import com.effigoglobal.learningportal.dtos.UserResponseDto;
import com.effigoglobal.learningportal.entities.CourseEntity;
import com.effigoglobal.learningportal.entities.EnrollmentEntity;
import com.effigoglobal.learningportal.entities.FavoriteEntity;
import com.effigoglobal.learningportal.entities.UserEntity;
import com.effigoglobal.learningportal.services.CourseService;
import com.effigoglobal.learningportal.services.EnrollmentService;
import com.effigoglobal.learningportal.services.FavouriteService;
import com.effigoglobal.learningportal.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/learningportal/users")
public class UserController {

	private UserService userService;
	private EnrollmentService enrollmentService;
	private FavouriteService favouriteService;
	private CourseService courseService;

	@GetMapping
	public ResponseEntity<Object> showAllUsers() {
		List<UserEntity> users = userService.findAllUsers();
		List<UserResponseDto> userresp = users.stream().map(userService::userEntitytoDtoMapper)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(userresp);

	}

	@PostMapping("/add")
	public ResponseEntity<Object> addUser(@RequestBody UserRequestDto userRequestDto) {

		log.info("User Added Successfully !!!");
		UserEntity userEntity = new UserEntity();
		userEntity.setName(userRequestDto.getName());
		userEntity.setRole(userRequestDto.getRole());
		UserEntity user = userService.addUser(userEntity);
		EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
		enrollmentEntity.setUserEntity(user);
		enrollmentService.saveEnrollment(enrollmentEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> showUserById(@PathVariable(value = "id") Long id) {
		Optional<UserEntity> userEntity = userService.findUserById(id);
		if (userEntity.isPresent()) {
			UserEntity user = userEntity.get();
			UserResponseDto userresp = new UserResponseDto();
			userresp.setId(user.getId());
			userresp.setName(user.getName());
			userresp.setRole(user.getRole());
			userresp.setEnrolledCourses(enrollmentService.findEnrolledCoursesByUser(user));
			List<CourseEntity> courses = user.getFavoriteCourses().stream().map(pred -> pred.getCourseFavEntity())
					.collect(Collectors.toList());
			userresp.setFavoriteCourses(
					courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList()));
			return ResponseEntity.status(HttpStatus.FOUND).body(userresp);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long id) {
		log.info("User Deleted Successfully !!!");
		userService.removeUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User deleted");
	}

	@PostMapping("/{id}/enroll/{courseid}")
	public ResponseEntity<Object> enrollCourse(@PathVariable(value = "id") Long id,
			@PathVariable(value = "courseid") Long courseId) {
		try {
			Optional<UserEntity> userEntity = userService.findUserById(id);
			Optional<CourseEntity> courseEntity = courseService.findCourseById(courseId);

			if (userEntity.isPresent() && courseEntity.isPresent()) {
				UserEntity user = userEntity.get();
				CourseEntity course = courseEntity.get();

				if (enrollmentService.checkEnrollmentByUserAndCourse(user, course)) {
					return ResponseEntity.badRequest().body("User is already enrolled in the course.");
				} else if (enrollmentService.checkEnrollmentByUser(user)) {
					List<EnrollmentEntity> enrollments = enrollmentService.findEnrollmentByUserEntity(user);
					Optional<EnrollmentEntity> enrollmentOpt = enrollments.stream()
							.filter(reg -> reg.getCourseEntity() == null).findFirst();
					EnrollmentEntity enrollmentEntity;
					if (enrollmentOpt.isEmpty()) {
						enrollmentEntity = new EnrollmentEntity();
						enrollmentEntity.setUserEntity(user);
					} else {
						enrollmentEntity = enrollmentOpt.get();
					}
					enrollmentEntity.setCourseEntity(course);
					enrollmentService.saveEnrollment(enrollmentEntity);

					UserResponseDto userresp = new UserResponseDto();
					userresp.setId(user.getId());
					userresp.setName(user.getName());
					userresp.setRole(user.getRole());
					userresp.setEnrolledCourses(enrollmentService.findEnrolledCoursesByUser(user));
					List<CourseEntity> courses = user.getFavoriteCourses().stream()
							.map(pred -> pred.getCourseFavEntity()).collect(Collectors.toList());
					userresp.setFavoriteCourses(courses.stream().map(courseService::mapCourseEntitytoCourseDto)
							.collect(Collectors.toList()));
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(userresp);
				} else {
					EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
					enrollmentEntity.setUserEntity(user);
					enrollmentEntity.setCourseEntity(course);
					enrollmentService.saveEnrollment(enrollmentEntity);

					UserResponseDto userresp = new UserResponseDto();
					userresp.setId(user.getId());
					userresp.setName(user.getName());
					userresp.setRole(user.getRole());
					userresp.setEnrolledCourses(enrollmentService.findEnrolledCoursesByUser(user));
					List<CourseEntity> courses = user.getFavoriteCourses().stream()
							.map(pred -> pred.getCourseFavEntity()).collect(Collectors.toList());
					userresp.setFavoriteCourses(courses.stream().map(courseService::mapCourseEntitytoCourseDto)
							.collect(Collectors.toList()));
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(userresp);
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Course not found!");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling user in the course.");
		}
	}

	@PostMapping("/{userId}/favorite/{courseId}")
	public ResponseEntity<Object> favoriteCourse(@PathVariable("userId") Long userId,
			@PathVariable("courseId") Long courseId) {
		Optional<UserEntity> userEntityOptional = userService.findUserById(userId);
		Optional<CourseEntity> courseEntityOptional = courseService.findCourseById(courseId);

		if (userEntityOptional.isPresent() && courseEntityOptional.isPresent()) {
			UserEntity user = userEntityOptional.get();
			CourseEntity course = courseEntityOptional.get();

			if (enrollmentService.checkEnrollmentByUserAndCourse(user, course)) {
				if (favouriteService.checkFavouriteByUserAndCourse(user, course)) {
					log.info("@UserController - Course is already a favorite.");
					return ResponseEntity.badRequest().body("Course is already a favorite.");
				}
				FavoriteEntity favourite = new FavoriteEntity();
				favourite.setUserFavEntity(user);
				favourite.setCourseFavEntity(course);
				favouriteService.saveFavourite(favourite);
				user.getFavoriteCourses().add(favourite);
				userService.addUser(user);
				UserResponseDto userresp = userService.userEntitytoDtoMapper(user);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(userresp);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not enroll in this course !!");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Course not found.");
		}

	}

}
