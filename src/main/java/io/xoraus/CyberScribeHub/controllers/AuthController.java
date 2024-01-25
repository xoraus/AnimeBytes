package io.xoraus.CyberScribeHub.controllers;

import io.xoraus.CyberScribeHub.entities.User;
import io.xoraus.CyberScribeHub.exceptions.ApiException;
import io.xoraus.CyberScribeHub.payloads.JwtAuthRequest;
import io.xoraus.CyberScribeHub.payloads.JwtAuthResponse;
import io.xoraus.CyberScribeHub.payloads.UserDto;
import io.xoraus.CyberScribeHub.repositories.UserRepo;
import io.xoraus.CyberScribeHub.security.JwtTokenHelper;
import io.xoraus.CyberScribeHub.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final UserRepo userRepo;

    private final ModelMapper mapper;


    @Autowired
    public AuthController(JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, UserService userService, UserRepo userRepo, ModelMapper mapper) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setUser(this.mapper.map(userDetails, UserDto.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        try {

            this.authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details !!");
            throw new ApiException("Invalid username or password !!");
        }

    }

    // register new user api

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // get logged-in user data
    @GetMapping("/current-user/")
    public ResponseEntity<UserDto> getUser(Principal principal) {
        User user = this.userRepo.findByEmail(principal.getName()).get();
        return new ResponseEntity<>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
    }

}
