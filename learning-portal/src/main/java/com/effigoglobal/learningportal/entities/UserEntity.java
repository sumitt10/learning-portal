package com.effigoglobal.learningportal.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_details")
public class UserEntity {

	public enum Roles {
		ADMIN, LEARNER, AUTHOR
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private Roles role;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<EnrollmentEntity> enrolledCourses;

	@OneToMany(mappedBy = "userFavEntity", cascade = CascadeType.ALL)
	private List<FavoriteEntity> favoriteCourses;

	@Column(name = "created_date")
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm")
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	@CreatedDate
	private LocalDateTime createdDate;

}
