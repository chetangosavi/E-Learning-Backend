package com.spring.boot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.entity.User;
import com.spring.boot.repositories.AuthRepository;
import com.spring.boot.services.AuthService;
import com.spring.boot.services.impl.UserDetailsServiceImpl;
import com.spring.boot.utils.JwtUtil;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private  AuthService authService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user){
		
		try {
			
			Optional<User> isPresentUser = authRepository.findByEmail(user.getEmail());
//			System.out.println("Found user in Database "+isPresentUser);
			
			if(isPresentUser.isPresent()) {
				return new ResponseEntity<String>("User Already Present With Same Email",HttpStatus.CONFLICT);
			}
			authService.registerUser(user);
			return new ResponseEntity<>("User Registered Successfully",HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Somthing Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		
		try {
			
//			System.out.println("User in Request body in login : " + user);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getEmail());
			String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(jwtToken,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Somthing Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
