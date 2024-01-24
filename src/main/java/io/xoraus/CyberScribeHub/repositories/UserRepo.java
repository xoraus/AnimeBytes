package io.xoraus.CyberScribeHub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.xoraus.CyberScribeHub.entities.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
}
