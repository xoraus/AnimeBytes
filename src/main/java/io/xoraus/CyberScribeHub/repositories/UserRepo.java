package io.xoraus.CyberScribeHub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.xoraus.CyberScribeHub.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
