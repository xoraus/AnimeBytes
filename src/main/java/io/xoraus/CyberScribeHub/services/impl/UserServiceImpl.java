package io.xoraus.CyberScribeHub.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import io.xoraus.CyberScribeHub.exceptions.ResourceNotFoundException;
import io.xoraus.CyberScribeHub.entities.User;
import io.xoraus.CyberScribeHub.payloads.UserDto;
import io.xoraus.CyberScribeHub.repositories.UserRepo;
import io.xoraus.CyberScribeHub.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;
	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
		this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}
	
	public User dtoToUser(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);
	}
	
	public UserDto userToDto(User user) {
		return this.modelMapper.map(user, UserDto.class);
	}
}
