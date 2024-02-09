package com.effigoglobal.learningportal.services;

import java.util.List;
import java.util.Optional;

import com.effigoglobal.learningportal.dtos.UserResponseDto;
import com.effigoglobal.learningportal.entities.UserEntity;

public interface UserService {
	public List<UserEntity> findAllUsers();

	public Optional<UserEntity> findUserById(Long id);

	public UserEntity addUser(UserEntity userEntity);

	public UserResponseDto userEntitytoDtoMapper(UserEntity userEntity);

	public void removeUserById(Long id);
}
