package io.xoraus.CyberScribeHub.security;

import io.xoraus.CyberScribeHub.entities.User;
import io.xoraus.CyberScribeHub.exceptions.ResourceNotFoundException;
import io.xoraus.CyberScribeHub.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User ", " email : " + username, 0));
    }

}
