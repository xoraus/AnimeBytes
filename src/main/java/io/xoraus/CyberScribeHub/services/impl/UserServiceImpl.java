package io.xoraus.CyberScribeHub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.xoraus.CyberScribeHub.entities.User;
import io.xoraus.CyberScribeHub.payloads.UserDto;
import io.xoraus.CyberScribeHub.repositories.UserRepo;
import io.xoraus.CyberScribeHub.services.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto user) {
		return null;
	}

	@Override
	public UserDto update(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		return user;
	}
	
	public UserDto userToDto(User user) {
	    UserDto userDto = new UserDto();
	    userDto.setId(user.getId());
	    userDto.setName(user.getName());
	    userDto.setEmail(user.getEmail());
	    userDto.setPassword(user.getPassword());
	    userDto.setAbout(user.getAbout());

	    return userDto;
	}


}
