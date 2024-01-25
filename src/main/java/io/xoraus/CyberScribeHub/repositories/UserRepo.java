package io.xoraus.CyberScribeHub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.xoraus.CyberScribeHub.entities.User;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);

}
