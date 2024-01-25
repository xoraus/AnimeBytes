package io.xoraus.CyberScribeHub.services;

import java.util.List;

import io.xoraus.CyberScribeHub.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

	UserDto updateUser(UserDto userDto, Integer uid);
}
