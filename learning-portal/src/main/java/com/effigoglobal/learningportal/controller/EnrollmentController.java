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

	private EnrollmentService EnrollmentService;

	@GetMapping
	public ResponseEntity<Object> showAllRegistrations() {
		log.info("Showing all the Enrollments !!");
		List<EnrollmentEntity> enrollments = EnrollmentService.findAllEnrollments();
		if (!enrollments.isEmpty()) {
			List<EnrollmentResponseDto> enrollmentsresp = enrollments.stream()
					.map(EnrollmentService::mapEnrollmentEntitytoDto).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.FOUND).body(enrollmentsresp);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to get Enrollments !!");
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> showEnrollmentById(@PathVariable(value = "id") Long id) {
		Optional<EnrollmentEntity> enrollmentEntity = EnrollmentService.findEnrollmentById(id);
		if (enrollmentEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enrollment not found!!");
		}
		EnrollmentEntity reg = enrollmentEntity.get();
		EnrollmentResponseDto enrollresp = EnrollmentService.mapEnrollmentEntitytoDto(reg);
		log.info("@EnrollmentController - Registration found.");
		return ResponseEntity.status(HttpStatus.FOUND).body(enrollresp);
	}

}
