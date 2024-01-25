package io.xoraus.CyberScribeHub.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import io.xoraus.CyberScribeHub.config.AppConstants;
import io.xoraus.CyberScribeHub.entities.User;
import io.xoraus.CyberScribeHub.exceptions.ResourceNotFoundException;
import io.xoraus.CyberScribeHub.payloads.UserDto;
import io.xoraus.CyberScribeHub.repositories.RoleRepo;
import io.xoraus.CyberScribeHub.repositories.UserRepo;
import io.xoraus.CyberScribeHub.entities.Role;
import io.xoraus.CyberScribeHub.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final ModelMapper modelMapper;

	private final PasswordEncoder passwordEncoder;

	private final RoleRepo roleRepo;

	@Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
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
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
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
		List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + AppConstants.NORMAL_USER));
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}