package com.effigoglobal.learningportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigoglobal.learningportal.dtos.EnrollmentResponseDto;
import com.effigoglobal.learningportal.entities.EnrollmentEntity;
import com.effigoglobal.learningportal.services.EnrollmentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("learningportal/Enrollment")
public class EnrollmentController {

	private EnrollmentService enrollmentService;

	@GetMapping
	public ResponseEntity<Object> findAllEnrollments() {
		log.info("Showing all the Enrollments !!");
		List<EnrollmentEntity> enrollments = enrollmentService.findAllEnrollments();
		if (!enrollments.isEmpty()) {
			List<EnrollmentResponseDto> enrollmentsresp = enrollments.stream()
					.map(enrollmentService::mapEnrollmentEntitytoDto).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.FOUND).body(enrollmentsresp);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to get Enrollments !!");
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> showEnrollmentById(@PathVariable(value = "id") Long id) {
		Optional<EnrollmentEntity> enrollments = enrollmentService.findEnrollmentById(id);

		if (enrollments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No enrollments found for the user with ID: " + id);
		}

		List<EnrollmentResponseDto> enrollmentResponseDtos = enrollments.stream()
				.map(enrollmentService::mapEnrollmentEntitytoDto).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.FOUND).body(enrollmentResponseDtos);
	}
}
