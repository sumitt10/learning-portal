package com.effigoglobal.learningportal.dtos;

import com.effigoglobal.learningportal.entities.UserEntity;

import lombok.Data;

@Data
public class UserRequestDto {

	private String name;
	private UserEntity.Roles role;
}
